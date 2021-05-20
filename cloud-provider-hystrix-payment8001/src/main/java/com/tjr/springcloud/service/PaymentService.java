package com.tjr.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author tanjirui
 * @create 2020-12-31
 */
@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池:"+Thread.currentThread().getName()+" paymentInfo_OK"+id+"\t" + "O(∩_∩)O哈哈~";
    }

    /**
     *
     * 一旦调用服务方法失败并抛出了错误信息后,会自动调用@HystrixCommand标注好的fallbckMethod调用类中的指定方法
     * 制造了两个异常:
     * 1.int a = 10/0;计算异常
     * 2.我们能接受3秒钟,它运行5秒钟,超时异常
     * 对于这两种异常,都能够调用fallback制定的方法.
     * 访问超时
     * @param id
     * @return
     */
    //设置超过3秒钟,采用服务降级
    @HystrixCommand(fallbackMethod="paymentInfo_timeoutHandler",commandProperties={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_timeout(Integer id){
//        int a = 10/0;
        int timeNumber = 3;
        try {
            // 暂停5秒钟
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id:" + id + "\t" + "O(∩_∩)O哈哈~  耗时(秒)" + timeNumber;

    }

    /**
     * 回调方法,用作异常调用,当前服务不可用,做服务降级,兜底的方法
     * @param id
     * @return
     */
    public String paymentInfo_timeoutHandler(Integer id){
        return "调用支付接口超时或异常,调用服务降级成功,当前线程名称为: "+Thread.currentThread().getName();
    }

//============================

    /**
     * 服务熔断
     * 相关参数属性在HystrixCommandProperties.java这个类中可以查询
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id <0) {
            throw new RuntimeException("***** id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负数.....";
    }
}
