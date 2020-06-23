package com.spring.module.gateway.tigerfilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author 付林虎
 * @Description //TODO 只需要被ioc容器管理即可 不用再 配置文件进行配置
 * @Date 2020/6/19 13:41
 * @Param
 * @Version V1.0
 * @return
 **/
@Component
@Slf4j
public class AuthGateWayFilter implements GlobalFilter,Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> token = exchange.getRequest().getHeaders().get("token");
        if(StringUtils.isEmpty(token)) {
            throw new RuntimeException("token is null");
        }else {
            log.info("token:{}",token);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
