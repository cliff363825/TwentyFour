package com.onevgo.springboot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>1. 开启基于注解的缓存 @EnableCaching
 * <p>2. 标注缓存注解即可 @Cacheable @CacheEvict @CachePut
 * <p>CacheManager: 默认 ConcurrentMapCacheManager
 * <p>Cache: 默认 ConcurrentMapCache，将数据保存在 ConcurrentMap<Object, Object>中
 *
 * <p>开发中使用缓存中间件：redis、memcached、ehcache</p>
 *
 * <p>整合redis作为缓存</p>
 * <p>Redis是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。</p>
 * <ul>
 * <li>1. 安装redis: 使用docker</li>
 * <li>2. 引入redis的starter: spring-boot-starter-data-redis</li>
 * <li>3. 配置redis</li>
 * <li>4. 测试缓存</li>
 * </ul>
 *
 * <p>小结
 * <ul>
 * <li>1. 引入redis的starter，容器中保存的是 RedisCacheManager;</li>
 * <li>2. RedisCacheManger 帮我们创建 RedisCache 来作为缓存组件；RedisCache 通过操作redis缓存数据</li>
 * <li>3. 默认保存数据 k-v 都是 Object；利用序列化保存；如何保存为json</li>
 * <ul>
 * <li>1. 引入redis的starter，CacheManager变为 RedisCacheManager</li>
 * <li>2. 默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object,Object>;</li>
 * <li>3. RedisTemplate<Object,Object>是默认使用jdk的序列化机制</li>
 * </ul>
 * <li>4. 自定义 CacheManager</li>
 * </ul>
 */
@SpringBootApplication
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}
