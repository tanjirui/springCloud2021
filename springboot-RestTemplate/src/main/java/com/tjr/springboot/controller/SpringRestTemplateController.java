package com.tjr.springboot.controller;

import com.tjr.springboot.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author tanjirui
 * @create 2021-01-06
 */
@Slf4j
@RestController
public class SpringRestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/testGetApi")
    public String getJson(){
        String cityName="北京";
        String key = "bf54ab34ff61c28c16a491352a987033";
        String url = "http://v.juhe.cn/weather/index" + "?key=" + key + "&cityname=" + cityName;
        log.info("请求url:" + url);

        String result = HttpClientUtil.doGet(url);
        log.info("result===>"+result);
        return result;
    }
}
