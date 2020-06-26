package com.lsd.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.lsd.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
