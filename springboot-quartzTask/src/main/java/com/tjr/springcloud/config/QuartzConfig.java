package com.tjr.springcloud.config;

import com.tjr.springcloud.quartz.QuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanjirui
 * @create 2021-01-14
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(QuartzJob.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1) //每一秒执行一次
                .repeatForever(); //永久重复，一直执行下去

        /**
         * SimpleTrigger和CronTrigger的区别：SimpleTrigger在具体的时间点执行一次或按指定时间间隔执行多次，CronTrigger按Cron表达式的方式去执行更常用。
         */

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                //.withSchedule(scheduleBuilder)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
    }

}
