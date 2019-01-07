package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 *  @author zhoulk
 *  @date 2019/1/3
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/login")
    public String login()
    {
        //hello-service为服务提供者的服务名称
        return "我是一个消费者去调用==》"+restTemplate.getForEntity("http://hello-service/user/login",String.class);
    }
}
