package com.lsd.springcloud.alibaba.service;

import com.lsd.springcloud.alibaba.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
