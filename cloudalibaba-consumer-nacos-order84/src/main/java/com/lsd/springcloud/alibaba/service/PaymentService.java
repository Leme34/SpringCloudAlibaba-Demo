package com.lsd.springcloud.alibaba.service;

import com.lsd.springcloud.entities.CommonResult;
import com.lsd.springcloud.entities.Payment;
import com.lsd.springcloud.alibaba.service.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult< Payment > paymentSQL(@PathVariable("id") Long id);

}
