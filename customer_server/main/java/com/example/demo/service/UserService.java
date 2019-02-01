package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * @author  zhoulk
 * @date 2019/1/16
*/
@Service("userService")
public class UserService {
    /**获取logger实例*/
    private final Logger logger = Logger.getLogger(getClass().toString());

    @Autowired
    private RestTemplate restTemplate;

    /**
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "notFindFallback")
    public String login()
    {
        logger.info("调用：http://hello-service/user/login");
        return "我是一个消费者去调用==》"+restTemplate.getForEntity("http://hello-service/user/login",String.class);
    }

    public String notFindFallback()
    {
        return "error";
    }
}
