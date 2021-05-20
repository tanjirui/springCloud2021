package com.tjr.schedule;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tanjirui
 * @create 2021-01-05
 */
@SpringBootApplication
@EnableScheduling
public class ScheduleMain {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleMain.class, args);
    }
}
