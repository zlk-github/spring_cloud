package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Administrator zhoulk
 * date: 2019/3/27 0027
 */
@FeignClient("hello-service")//指定服务hello-service
public interface UserService {

    /**
     *  hello-service服务对应接口/user/login
     */
    @RequestMapping("/user/login")
    public String login();
}
