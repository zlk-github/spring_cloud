package com.example.demo.service;

import com.example.demo.bean.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**通过继承HystrixCommand的方式实现请求的同步与异步执行(未测试通过)
 * @author  zhoulk
 * @date 2019/2/12
 */
public class UserCommand extends HystrixCommand<User> {
    @Autowired
    private RestTemplate restTemplate;

    public UserCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("UserCommand"));
    }

    @Override
    protected User run() throws Exception {
        System.out.println("");
        return  restTemplate.getForObject("http://hello-service/user/login",User.class);
    }

    @Override
    protected User getFallback() {
        System.out.println("==================================");
        System.out.println(getFailedExecutionException().getMessage());
        return new User("def","def");
    }
}
