package com.springboot.redis.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/3/29 14:14
 * @Version V1.0
 */
@Configuration
public class RedisConfig {
    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        // timeout，这里既是连接超时又是读写超时，从Jedis 2.8开始有区分connectionTimeout和soTimeout的构造函数
        return new JedisPool(jedisPoolConfig, "192.168.232.140", 6379, 3000, null);
    }


    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.232.140:6379").setDatabase(0);
        /*config.useClusterServers()
                .addNodeAddress("redis://192.168.0.61:8001")
                .addNodeAddress("redis://192.168.0.62:8002")
                .addNodeAddress("redis://192.168.0.63:8003")
                .addNodeAddress("redis://192.168.0.61:8004")
                .addNodeAddress("redis://192.168.0.62:8005")
                .addNodeAddress("redis://192.168.0.63:8006");*/
        return (Redisson) Redisson.create(config);
    }
}
