package com.example.demo.service;

import com.example.demo.CustomerApplication;
import com.example.demo.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * @author  zhoulk
 * @date 2019/2/12
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CustomerApplication.class})
@WebAppConfiguration*/
//@SpringApplicationConfiguration(classes = GnTdcServerApplication.class)  //该注解配置了项目启动类的位置，这个很重要，不然连mapperscan都找不到
//@WebAppConfiguration   //加载servlet-context
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCommandTest {

    @Autowired
    private UserService userService;

    /**
     * 测试同步执行
     */
    @Test
    public void testSynchronous() {
        String login = userService.login();
        System.out.println("==============================================");
        System.out.println(login);
        //System.out.println(new UserCommand().execute());
    }

    /**
     * 测试异步执行
     */
    @Test
    public void testAsynchronous() throws ExecutionException, InterruptedException {
        Future<User> fWorld = new UserCommand().queue();
        //一步执行用get()来获取结果
        User user = fWorld.get();
        System.out.println(user);
    }

}
