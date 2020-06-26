package com.lsd.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手写负载均衡的【轮询】算法
 */
@Component
public class MyLoadBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;  //越界重置计数
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("****第几次访问，次数next: " + next);
        return next;
    }

    // 轮询算法，接口第几次请求数 % 服务器集群总数 = 实际调用服务器位置下标
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
