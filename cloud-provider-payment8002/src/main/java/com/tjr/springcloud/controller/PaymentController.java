package com.tjr.springcloud.controller;

import com.tjr.springcloud.entities.CommonResult;
import com.tjr.springcloud.entities.Payment;
import com.tjr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tanjirui
 * @create 2020-12-29
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //端口号,查看负载均衡效果
    @Value("${server.port}")
    private String serverPort;

    //服务发现,获取服务信息
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);

        // 判断是否插入成功
        if (result>0){
            return new CommonResult(200,"插入数据成功,serverPort"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败,serverPort"+serverPort,null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("*****查询结果："+result);
        // 判断是否插入成功
        if (result!=null){
            return new CommonResult(200,"查询成功,serverPort"+serverPort,result);
        }else {
            return new CommonResult(444,"没有对应记录，查询id："+id+"serverPort"+serverPort,null);
        }
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("获取到的服务====>"+service);
        }
        //一个微服务下的全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"==>"+instance.getHost()+"==>"+instance.getPort()+"==>"+instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     *手写ribbon负载均衡算法,获取端口号
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
