package com.spring.module.gateway.predicatesfactory;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @className
 * @Description TODO 老虎自定义谓词工厂,业务模拟12306 晚上23:00到凌晨6:00不能购票
 * @Author 付林虎
 * @Date 2020/6/19 8:26
 * @Version V1.0
 */
@Component
public class TigerBetweenTimeRoutePredicateFactory extends AbstractRoutePredicateFactory<TigerBetweenTimeConfig> {
    public TigerBetweenTimeRoutePredicateFactory() {
        super(TigerBetweenTimeConfig.class);
    }
    //主要实现业务逻辑
    @Override
    public Predicate<ServerWebExchange> apply(TigerBetweenTimeConfig config) {
        LocalTime startTime = config.getStartTime();
        LocalTime endTime = config.getEndTime();

        return new Predicate<ServerWebExchange>(){
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                LocalTime now = LocalTime.now();

                return now.isAfter(startTime) && now.isBefore(endTime);
            }
        };
    }

    //Config对应的字段 接收传递参数
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("startTime", "endTime");
    }
}
