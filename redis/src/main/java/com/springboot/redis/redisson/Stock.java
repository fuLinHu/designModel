package com.springboot.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/3/29 19:46
 * @Version V1.0
 */
@Component
public class Stock {

    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String deductStock() throws InterruptedException {
        String lockKey = "product_001";
        RLock redissonLock = redisson.getLock(lockKey);
        try {

            // 加锁，实现锁续命功能
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock + "");
            } else {
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            redissonLock.unlock();
        }
        return "end";
    }

}
