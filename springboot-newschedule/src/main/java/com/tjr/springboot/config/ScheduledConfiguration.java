package com.tjr.springboot.config;

import com.tjr.springboot.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author tanjirui
 * @create 2021-01-05
 */
@Configuration
@EnableScheduling
public class ScheduledConfiguration {



    @Autowired
    private SayHelloService sayHelloService;

    @Scheduled(cron = "0/2 * * * * ?")
    public void sayHello(){
        sayHelloService.sayHello();
    }
}
