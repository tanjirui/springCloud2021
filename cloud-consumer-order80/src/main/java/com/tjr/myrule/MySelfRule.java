package com.tjr.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡路由规则类
 * @author tanjirui
 * @create 2020-12-30
 */
@Configuration
public class MySelfRule {

    public IRule myRule(){
        //定义为随机
        return new RoundRobinRule();
    }

}
