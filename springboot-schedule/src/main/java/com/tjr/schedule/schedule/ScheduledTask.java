package com.tjr.schedule.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tanjirui
 * @create 2021-01-05
 */
@Component
public class ScheduledTask {

    Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    /*@Scheduled(cron = "0/2 * * * * ?")
    //@Scheduled(fixedDelay = 1000 )
    public void scheduledTask1(){
        System.out.println("cron打印时间:"+new Date());
    }*/

    @Scheduled(fixedDelay = 1000)  //定时任务1
    public void printXXXXXXX(){
        try{
            Thread.sleep(5000);  //睡眠5秒
            //logger.info(Thread.currentThread().getName()); //打印当前线程名字
            logger.info("printXXXXXXX");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 1000)  //定时任务2
    public void printYYYYYYY(){
        try{
            Thread.sleep(5000);
            //logger.info(Thread.currentThread().getName());
            logger.info("printYYYYYYY");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }



}
