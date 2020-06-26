package com.lsd.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lsd.springcloud.alibaba.service.PaymentService;
import com.lsd.springcloud.entities.CommonResult;
import com.lsd.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Sentinel+OpenFeign的服务调用方
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")     //若什么都没配置，则既没有限流也没有降级
//    @SentinelResource(value = "fallback",fallback ="handlerFallback")  //只配置fallback则运行异常会降级
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",  //fallback管理运行异常时的降级
            blockHandler = "blockHandler", //blockHandler管理限流配置违规的降级，若既抛出异常也配置违规则走限流
            exceptionsToIgnore = {IllegalArgumentException.class}  //忽略的异常
    )
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    /**
     * Sentinel整合OpenFeign
     */
    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }

    public CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "异常handlerFallback，exception内容： " + e.getMessage(), payment);
    }


    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }

}
