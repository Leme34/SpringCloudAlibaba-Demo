package com.lsd.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced   //Nacos的负载均衡是通过集成Ribbon实现的
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
