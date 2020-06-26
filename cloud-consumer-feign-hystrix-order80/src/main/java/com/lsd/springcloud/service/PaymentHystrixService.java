package com.lsd.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//fallback指定统一的服务降级实现，解耦代码
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = com.lsd.springcloud.service.PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);

}
