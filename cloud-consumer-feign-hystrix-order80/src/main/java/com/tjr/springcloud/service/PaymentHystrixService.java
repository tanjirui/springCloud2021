package com.tjr.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tanjirui
 * @create 2021-01-04
 * fallback表示的是调用服务出现异常回调的类,统一为接口里面的方法进行异常处理
 */
@Component
@FeignClient(value ="CLOUD-PROVIDER-HYSTRIX-PAYMENT" ,fallback = PaymentHystrixServiceImp.class)
public interface PaymentHystrixService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    /**
     * 超时访问
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id);




}
