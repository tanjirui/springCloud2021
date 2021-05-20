package com.tjr.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 服务降级,客户端去调用服务端,碰上服务端宕机或关闭
 * 只需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可实现代码解耦
 * @author tanjirui
 * @create 2021-01-04
 */
@Component
public class PaymentHystrixServiceImp implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_OK,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_Timeout,o(╥﹏╥)o";
    }
}
