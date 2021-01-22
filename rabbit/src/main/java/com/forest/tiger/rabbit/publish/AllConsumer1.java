package com.forest.tiger.rabbit.publish;

import com.forest.tiger.rabbit.utils.RabbitConstant;
import com.forest.tiger.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/30 16:27
 * @Version V1.0
 */
public class AllConsumer1 {
    public static void main(String[] args) {
        //获取现有的政策
        //1）开启一个新线程  扫描库中的舆情（舆情时间大于政策的时间的舆情）定时，没扫描一批 根据时间进行标准断点 以至于下一次启动不会重读采集
        //2）将获取的舆情  根据 hash(id)/n  ---》指定的队列中,那么分到一个队列中的数据的id不可能分到其他队列中，且数据是先进先出
        //3）然后 一个队列只有一个消费者 向数据库中存数据（先根据id获取数据中数据，然后再把舆情和政策关联字段进行去重叠加进行修改）
        //当新增一个政策 则新开一个线程  重复 1，2，3步骤。

    }
}
