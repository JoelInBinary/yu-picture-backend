package com.joel.yupicturebackend.manager;

import cn.hutool.json.JSONUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
public class CacheManager {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 初始化 Caffeine 本地缓存
    private final Cache<String, String> LOCAL_CACHE = Caffeine.newBuilder()
            .maximumSize(1000L)
            .expireAfterWrite(5L, TimeUnit.MINUTES) // 缓存 5 分钟移除
            .build();

    /**
     * 通用的二级缓存查询方法
     * @param cacheKeyPrefix 缓存键前缀
     * @param queryCondition 查询条件
     * @param resultClass 结果类型
     * @param dbFallback 数据库查询回调
     * @param <T> 结果泛型
     * @return 缓存或数据库查询结果
     */
    public <T> T getWithCache(String cacheKeyPrefix, Object queryCondition, Class<T> resultClass, Supplier<T> dbFallback) {
        // 构建缓存 key
        String queryJson = JSONUtil.toJsonStr(queryCondition);
        String hashKey = DigestUtils.md5DigestAsHex(queryJson.getBytes());
        String cacheKey = String.format("%s:%s", cacheKeyPrefix, hashKey);

        // 1. 从本地缓存中查询
        String cachedValue = LOCAL_CACHE.getIfPresent(cacheKey);
        if (cachedValue != null) {
            return JSONUtil.toBean(cachedValue, resultClass);
        }

        // 2. 本地缓存未命中，从 Redis 缓存中查询
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        cachedValue = valueOps.get(cacheKey);
        if (cachedValue != null) {
            // 如果 Redis 缓存命中，将结果存入本地缓存
            LOCAL_CACHE.put(cacheKey, cachedValue);
            return JSONUtil.toBean(cachedValue, resultClass);
        }

        // 3. Redis 缓存也未命中，查询数据库
        T result = dbFallback.get();

        if (result != null) {
            // 4. 更新缓存
            String cacheValue = JSONUtil.toJsonStr(result);
            // 5 - 10 分钟随机过期，防止缓存雪崩
            int cacheExpireTime = 300 + (int) (Math.random() * 300);
            valueOps.set(cacheKey, cacheValue, cacheExpireTime, TimeUnit.SECONDS);
            // 更新本地缓存
            LOCAL_CACHE.put(cacheKey, cacheValue);
        }
        return result;
    }
}