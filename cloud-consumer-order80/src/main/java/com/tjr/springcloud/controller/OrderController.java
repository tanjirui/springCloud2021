package com.tjr.springcloud.controller;

import com.tjr.springcloud.entities.CommonResult;
import com.tjr.springcloud.entities.Payment;
import com.tjr.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author tanjirui
 * @create 2020-12-29
 */
@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    // 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }

    /**
     * getForObject返回对象为响应体中的数据转化成的对象,基本上可以理解为json
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
     * getForEntity返回对象为ResponseEntity对象,包含了响应中的一些重要信息,比如响应头,响应状态码,响应体等
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id")Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return  new CommonResult(444,"操作失敗",null);
        }

    }

    /**
     * 手写ribbon负载均衡算法
     * @return
     */
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        // 通过容器中的 discoveryClient和服务名来获取服务集群
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
