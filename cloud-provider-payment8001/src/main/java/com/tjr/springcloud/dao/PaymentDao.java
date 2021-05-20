package com.tjr.springcloud.dao;

import com.tjr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tanjirui
 * @create 2020-12-29
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment  getPaymentById(@Param("id")Long id);
}
