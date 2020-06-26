package com.lsd.springcloud.alibaba.config;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lsd.springcloud.alibaba.dao"})
public class MyBatisConfig {


}
