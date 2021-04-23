package com.springboot.redis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/3/29 10:41
 * @Version V1.0
 */
@Component
public class HotKey {
    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {
        // 从Redis中获取数据
        String value = jedisPool.getResource().get(key);
        // 如果value为空， 则开始重构缓存
        if (value == null) {
            // 只允许一个线程重建缓存， 使用nx， 并设置过期时间ex
            String mutexKey = "mutext:key:" + key;
            String set = jedisPool.getResource().set(mutexKey, "fulinhu", "nx", "ex", 1800);

            if (set!=null) {
                // 从数据源获取数据
               // value = db.get(key);
                // 回写Redis， 并设置过期时间
                jedisPool.getResource().set(key, value);
                // 删除key_mutex
                jedisPool.getResource().del(mutexKey);
            }// 其他线程休息50毫秒后重试
            else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                get(key);
            }
        }
        return value;
    }

}
