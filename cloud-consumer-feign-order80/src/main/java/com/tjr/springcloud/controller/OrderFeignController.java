package com.tjr.springcloud.controller;

import com.tjr.springcloud.entities.CommonResult;
import com.tjr.springcloud.entities.Payment;
import com.tjr.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanjirui
 * @create 2020-12-30
 */
@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
       return  paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        return paymentFeignService.getPaymentLB();
    }

    /**
     * 客户端调用服务端超时,需要在yml设置超时控制
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
}
