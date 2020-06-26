package com.lsd.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * gateway自定义全局过滤器实现日志记录+简单认证过滤
 * <p>
 * Ordered接口用于指定加载过滤器的顺序
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    /**
     * 加载过滤器的顺序，数字越小，优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("****** come in MyLogGateWayFilter: " + new Date());

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null) {
            log.info("*****用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

}
