package com.lsd.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 官方警告：Ribbon负载均衡算法接口IRule的配置类不能放在Springboot组件扫描到的包！！！
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule(); // 定义为随机
    }

}
