package com.lsd.springcloud.alibaba.blockHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lsd.springcloud.entities.CommonResult;
import com.lsd.springcloud.entities.Payment;

/**
 * 使用自定义降级处理类使业务代码与降级代码解耦，注意官方文档规定降级方法要是静态方法
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "按照客户自定义的Glogal 全局异常处理 ---- 1", new Payment(2020L, "serial003"));
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "按照客户自定义的Glogal 全局异常处理 ---- 2", new Payment(2020L, "serial003"));
    }
}
