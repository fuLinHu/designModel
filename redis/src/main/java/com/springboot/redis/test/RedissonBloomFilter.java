package com.springboot.redis.test;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @className
 * @Description TODO 使用布隆过滤器得时候 时先知道有哪些key  把key放到过滤器中  当获取gaikey对应得值得时候  先判断key 是否在过滤器中，
 * @Description TODO 如果不存在直接返回，如果存在 再去  redis中获取  redis中不存在得时候在去  数据库获取
 * @Description TODO 布隆过滤器不能删除数据只能重建
 * @Description TODO 布隆过滤器   如果key存在则不一定存在   如果key不存在则一定不存在
 * @Author 付林虎
 * @Date 2021/3/29 15:23
 * @Version V1.0
 */
public class RedissonBloomFilter {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.232.140:6379");
        //构造Redisson
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("nameList");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%,根据这两个参数会计算出底层的bit数组大小
        bloomFilter.tryInit(100000000L,0.03);
        //将zhuge插入到布隆过滤器中
        bloomFilter.add("zhuge");

        //判断下面号码是否在布隆过滤器中
        System.out.println(bloomFilter.contains("guojia"));//false
        System.out.println(bloomFilter.contains("baiqi"));//false
        System.out.println(bloomFilter.contains("zhuge"));//true
    }

}
