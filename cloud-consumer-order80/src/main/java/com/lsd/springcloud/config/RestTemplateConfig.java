package com.lsd.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  //使用此注解启用RestTemplate的负载均衡机制
    public RestTemplate getRestTemplate() {
        return  new RestTemplate();
    }
}
