package com.example.demo.service;

import com.example.demo.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
    @CacheResult

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
    @HystrixCommand(ignoreExceptions = {Exception.class},commandKey = "",groupKey = "",threadPoolKey = "")
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
    @HystrixCommand(fallbackMethod = "notFindFallback",observableExecutionMode = ObservableExecutionMode.EAGER)
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

    @HystrixCommand(fallbackMethod = "notFindFallback2")
    public List<User> users(List<Long> ids){
        List forObject = restTemplate.getForObject("http://hello-service/user/users?ids={ids}", List.class, StringUtils.join(ids,","));
        return forObject;
    }

    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds", value = "100")
    })
    public Future<User> find(Long id) {
        return null;
    }

    @HystrixCommand
    public List<User> findAll(List<Long> ids) {
        List forObject = restTemplate.getForObject("http://hello-service/user/findAll?ids={ids}", List.class, StringUtils.join(ids,","));
        return forObject;
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
     * 异常触发方法
     * @return
     */
    public String notFindFallback2(List<Long> ids)
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
