package com.tjr.springboot.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tanjirui
 * @create 2021-01-05
 */
@Service
public class SayHelloService {

    public void sayHello(){
        System.out.println("Hello"+new Date());
    }

    public void loveYou(){
        System.out.println("I Love You");
    }

    public void likeYou(){
        System.out.println("I Like You");
    }

    public void happy520(){
        System.out.println("520,我爱你");
    }
}
