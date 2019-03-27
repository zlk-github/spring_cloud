package com.example.demo.controller;
import com.example.demo.bean.User;
import com.example.demo.service.UserCommand;
import com.example.demo.service.UserService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *  @author zhoulk
 *  @date 2019/1/3
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login()
    {
        //hello-service为服务提供者的服务名称
        //return "我是一个消费者去调用==》"+restTemplate.getForEntity("http://hello-service/user/login",String.class);
       /* String s = null;
        Future<String> stringFuture = userService.loginAsync();
        try {
            s = stringFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  s;*/
        //return  userService.login();
        String s = userService.login();
        return userService.login();
    }

    /**
     * * 用于测试GET请求
     * @param name 名称
     * @param password 密码
     * @return json
     */
    @RequestMapping("/getTest")
    public String getTest(String name,String password){
        //方便测试，消费者参数写死
      /*  Map<String, Object> requestMap = Maps.newHashMap();
        requestMap.put("name", "123456");
        requestMap.put("password", "xiao ming");
        //服务提供者map接收
         ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/user/getTest?name={name}&password={password}", String.class, requestMap);*/
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/user/getTest?name={name}&password={password}", String.class, "admin", "123456");
       /*
       //未测试通
       User user = new User();
        user.setName("admin");
        user.setPassword("1134253");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/user/getTest", String.class, user);*/
        String body = responseEntity.getBody();
        return body;
    }

    /**
     * * 用于测试POST请求
     * @param name 名称
     * @param password 密码
     * @return json
     */
    @RequestMapping("/postTest")
    public String postTest(String name,String password){
        User user = new User();
        user.setName("admin");
        user.setPassword("1134253");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://hello-service/user/postTest", user,String.class);
        String body = responseEntity.getBody();
        return body;
    }

    @RequestMapping("/users")
    public String users() throws ExecutionException, InterruptedException {
        //开启上下文TheardLocal
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<User> demo1 = userService.find(1001L);
        Future<User> demo2 = userService.find(1002L);
        System.out.println(demo1.get());
        System.out.println(demo2.get());
        context.close();
        return "";
    }
}
