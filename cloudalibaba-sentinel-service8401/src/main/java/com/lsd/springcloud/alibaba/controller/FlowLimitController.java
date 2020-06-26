package com.lsd.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 限流测试接口
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }

    /**
     * 熔断降级策略测试1：RT（平均响应时间）
     */
    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT熔断降级策略");
        return "----testD";
    }

    /**
     * 熔断降级策略测试2：异常比例/异常数
     * @return
     */
    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常数");
        int age = 10 / 0;
        return "----testE 测试异常数";
    }

    /**
     * 熔断降级策略测试3：热点key限流
     */
    @GetMapping("/testHotKey")
    //@SentinelResource只负责Sentinel控制台配置的热点key限流规则，若没有超限但是代码排除异常则也不会触发服务降级
    //value是Sentinel设置的资源名称，blockHandler必须配置否则降级时返回报错页面
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        return "----testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "----deal_testHotKey, o(╥﹏╥)o"; // sentinel的默认提示都是： Blocked by Sentinel (flow limiting)
    }
}
