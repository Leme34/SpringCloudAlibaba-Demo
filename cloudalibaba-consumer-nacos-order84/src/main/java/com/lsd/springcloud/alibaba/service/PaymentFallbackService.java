package com.lsd.springcloud.alibaba.service;

import com.lsd.springcloud.entities.CommonResult;
import com.lsd.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * PaymentService降级处理类
 */
@Component
public class PaymentFallbackService implements PaymentService {


    @Override
    public CommonResult< Payment > paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回，---PaymentFallbackService",new Payment(id,"ErrorSerial"));
    }
}
