package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;
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
     *同步调用
     * @return
     */
    @HystrixCommand(fallbackMethod = "notFindFallback1")
    public String login()
    {
        logger.info("调用：http://hello-service/user/login");
       // ResponseEntity<String> forEntity = restTemplate.getForEntity("http://hello-service/user/login", String.class);
        String forObject = restTemplate.getForObject("http://hello-service/user/login", String.class);
        return "我是一个消费者去调用==》"+forObject;
    }

    /**
     *ignoreExceptions指定忽略的异常,当异常满足时，不会触发notFindFallback
     * @return
     */
    @HystrixCommand(ignoreExceptions = {Exception.class})
    public String loginIngore()
    {
        logger.info("调用：http://hello-service/user/login");
        // ResponseEntity<String> forEntity = restTemplate.getForEntity("http://hello-service/user/login", String.class);
        String forObject = restTemplate.getForObject("http://hello-service/user/login", String.class);
        return "我是一个消费者去调用==》"+forObject;
    }

    /**
     *异步调用
     * @return
     */
    @HystrixCommand(fallbackMethod = "notFindFallback")
    public Future<String> loginAsync()
    {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                String forObject = restTemplate.getForObject("http://hello-service/user/login", String.class);
                return "我是一个消费者去调用==》"+forObject;
            }
        };
    }

    /**
     * 异常触发方法
     * @return
     */
    public String notFindFallback()
    {
        return "error";
    }

    /**
     * 异常捕获方法
     * @return
     */
    public String notFindFallback1(Throwable e)
    {
        return e.getMessage();
    }
}
