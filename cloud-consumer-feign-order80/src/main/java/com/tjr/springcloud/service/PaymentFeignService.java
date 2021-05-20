package com.tjr.springcloud.service;

import com.tjr.springcloud.entities.CommonResult;
import com.tjr.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程服务调用接口,调用8001和8002集群服务下的方法
 * @author tanjirui
 * @create 2020-12-30
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB();

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
