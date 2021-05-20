package com.tjr.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *手写ribbon负载均衡算法
 * @author tanjirui
 * @create 2020-12-30
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("******第几次访问,次数next:"+next);
        return next;
    }
    /**
     * 当前访问的这个服务集群中有多少台服务器实例
     * 负载均衡的算法:rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标,每次服务重启后,rest接口计数从1开始
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        //获取服务器位置下标
        int index = getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
