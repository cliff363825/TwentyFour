package com.onevgo.springboot.cache.service;

import com.onevgo.springboot.cache.bean.Department;
import com.onevgo.springboot.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    @Qualifier("departmentCacheManager")
    private RedisCacheManager deptCacheManager;

//    @Cacheable(cacheNames = "dept", cacheManager = "departmentCacheManager")
//    public Department getDeptById(Integer id) {
//        return departmentMapper.getDeptById(id);
//    }

    /**
     * <p>编程式操作缓存
     * <ul>
     * <li>1. 注入 CacheManager(RedisCacheManager)。</li>
     * <li>2. 通过 CacheManager.getCache(cacheName) 获取 Cache(RedisCache) 组件。</li>
     * <li>3. Cache 组件通过CRUD操作redis操作缓存。</li>
     * </ul>
     *
     * @param id
     * @return
     */
    public Department getDeptById(Integer id) {
        Cache cache = deptCacheManager.getCache("dept");
        Cache.ValueWrapper cacheHit = cache.get("dept:" + id);

        Object cacheValue;

        if (cacheHit != null) {
            cacheValue = cacheHit.get();
        } else {
            System.out.println("查询部门" + id);
            cacheValue = departmentMapper.getDeptById(id);

            cache.put("dept:" + id, cacheValue);
        }

        return (Department) cacheValue;
    }
}
