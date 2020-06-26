package com.lsd.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由配置类，作用等同于在application.yml配置
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址： http://news.baidu.com/guonei
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_lsd",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei"));
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_lsd2",
                r -> r.path("/guoji").uri("http://news.baidu.com/guoji"));
        return routes.build();
    }
}
