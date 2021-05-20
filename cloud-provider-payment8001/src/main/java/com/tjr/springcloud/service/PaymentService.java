package com.tjr.springcloud.service;

import com.tjr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author tanjirui
 * @create 2020-12-29
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment  getPaymentById(@Param("id")Long id);
}
