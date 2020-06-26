package com.lsd.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lsd.springcloud.alibaba.blockHandler.CustomerBlockHandler;
import com.lsd.springcloud.entities.CommonResult;
import com.lsd.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 详细演示@SentinelResource注解用法
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按照资源名称限流测试", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按照byUrl限流测试", new Payment(2020L, "serial002"));
    }

    /**
     * 使用自定义降级处理类使业务代码与降级代码解耦
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",      //资源名称
            blockHandlerClass = CustomerBlockHandler.class,  //降级处理类
            blockHandler = "handlerException2")      //该降级处理类中的降级处理方法
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按照客户自定义限流测试", new Payment(2020L, "serial003"));
    }
}
