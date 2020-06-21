package com.onevgo.springboot.cache.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * <code>@EnableCaching</code>: 启用Spring的注解驱动的缓存管理功能，类似于Spring的 <cache:*> XML名称空间中的支持。
 *
 * <p>org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration: 缓存抽象的自动配置。在通过 EnableCaching 启用缓存时，如果需要，创建一个 CacheManager。
 * <p>可以通过配置自动检测或显式指定缓存存储。
 *
 * <ul>
 * <li>mappings.put(CacheType.GENERIC, GenericCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.EHCACHE, EhCacheCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.HAZELCAST, HazelcastCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.INFINISPAN, InfinispanCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.JCACHE, JCacheCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.COUCHBASE, CouchbaseCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.REDIS, RedisCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.CAFFEINE, CaffeineCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.GUAVA, GuavaCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.SIMPLE, SimpleCacheConfiguration.class);</li>
 * <li>mappings.put(CacheType.NONE, NoOpCacheConfiguration.class);</li>
 * </ul>
 *
 * <p>org.springframework.cache.Cache: 定义公共缓存操作的接口。注意:由于缓存的一般用途，建议实现允许存储空值(例如用于缓存返回空值的方法)。
 * <p>org.springframework.cache.CacheManager: Spring的中央缓存管理器SPI。允许检索指定的缓存区域。
 * <p>CacheManager 管理多个 Cache 组件的，对缓存的真正CRUD操作是在 Cache 组件中，每个缓存组件有唯一一个名字。
 *
 * <p>SimpleCacheConfiguration: 默认生效的缓存配置类
 * <ul>
 * <li>给容器中注册了一个 CacheManager: ConcurrentMapCacheManager</li>
 * <li>获取和创建的 Cache: ConcurrentMapCache，他的作用将数据保存在 ConcurrentMap 中</li>
 * </ul>
 *
 * <p>org.springframework.cache.interceptor.CacheInterceptor: 使用公共Spring缓存基础设施(org.springframework.cache.Cache)的AOP Alliance MethodInterceptor用于声明式缓存管理。
 * <p>派生自 CacheAspectSupport 类，该类包含与Spring底层缓存API的集成。CacheInterceptor 只是按照正确的顺序调用相关的超类方法。
 * <p>CacheInterceptors是线程安全的。
 * <p>CacheInterceptor 执行过程:
 * <ul>
 * <li>processCacheEvicts(contexts.get(CacheEvictOperation.class), true, CacheOperationExpressionEvaluator.NO_RESULT); 从缓存上下文中获取缓存驱逐操作(CacheEvictOperation)，并优先处理这些缓存驱逐操作。</li>
 * <li>Cache.ValueWrapper cacheHit = findCachedItem(contexts.get(CacheableOperation.class)); 从缓存上下文中获取可缓存操作(CacheableOperation)，并从这些缓存操作中查询对应的缓存项。</li>
 * <li>1. CacheManager 根据 cacheNames 获取对应的 Cache 组件。</li>
 * <li>2. 如果 Cache 组件不存在，则自动创建。</li>
 * <li>3. 在 Cache 中查询 key 对应的缓存项。</li>
 * <li>key: 默认使用 SimpleKeyGenerator 生成key。</li>
 * <li>SimpleKeyGenerator 生成key的策略:</li>
 * <li>如果没有参数，key = new SimpleKey();</li>
 * <li>如果有一个参数，key = 参数的值</li>
 * <li>如果有多个参数，key = new SimpleKey(params);</li>
 * <li>collectPutRequests(contexts.get(CacheableOperation.class), CacheOperationExpressionEvaluator.NO_RESULT, cachePutRequests); 如果没有找到缓存项，则从任何可缓存操作(CacheableOperation)中收集缓存设置请求(CachePutRequest)</li>
 * <li>cacheHit != null && cachePutRequests.isEmpty() && !hasCachePut(contexts) 如果找到缓存项并且没有缓存设置请求(CachePutRequest)，则直接使用缓存项。</li>
 * <li>returnValue = invokeOperation(invoker); 如果找到缓存项或有缓存设置请求(CachePutRequest)，则执行方法获取返回值。 </li>
 * <li>collectPutRequests(contexts.get(CachePutOperation.class), cacheValue, cachePutRequests); 从缓存上下文中获取缓存设置操作(CachePutOperation)，并将其放入 cachePutRequests 中。</li>
 * <li>cachePutRequest.apply(cacheValue); 执行每一个 cachePutRequest 缓存设置操作。</li>
 * <li>processCacheEvicts(contexts.get(CacheEvictOperation.class), false, cacheValue); 从缓存上下文中获取缓存驱逐操作(CacheEvictOperation)，并处理这些缓存驱逐操作。</li>
 * </ul>
 *
 * <p>小结:
 * <li>1. 使用 CacheManager(ConcurrentMapCacheManager) 按照 cacheNames 得到 Cache(ConcurrentMapCache) 组件。</li>
 * <li>2. 使用 keyGenerator(SimpleKeyGenerator) 生成 key。</li>
 */
@Configuration
@EnableCaching
public class MyCacheConfig extends CachingConfigurerSupport {
    @Bean("myKeyGenerator")
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return target.getClass().getName() + "." + method.getName() +
                        "(" + StringUtils.arrayToCommaDelimitedString(params) + ")";
            }
        };
    }

    @Bean
    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
        return new CacheManagerCustomizer<RedisCacheManager>() {
            @Override
            public void customize(RedisCacheManager cacheManager) {
                cacheManager.setDefaultExpiration(3600);
            }
        };
    }

    @Bean
    @Primary
    public RedisCacheManager cacheManager(@Qualifier("redisTemplate") RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);

        return cacheManager;
    }


    @Bean
    public RedisCacheManager employeeCacheManager(@Qualifier("employeeRedisTemplate") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);

        return cacheManager;
    }

    @Bean
    public RedisCacheManager departmentCacheManager(@Qualifier("departmentRedisTemplate") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);

        return cacheManager;
    }
}
