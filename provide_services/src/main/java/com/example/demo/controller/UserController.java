package com.example.demo.controller;
import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *  @author zhoulk
 *  @date 2019/1/3
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**获取logger实例*/
    private final Logger logger = Logger.getLogger(getClass().toString());

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String hostname;


    //@Value("${ss}")
    private String ss;

    @RequestMapping("/login")
    public String login(){
        logger.info("服务host:"+port+",service_id:"+hostname+"ss:"+ss);
        System.out.println("port:"+port);
        return "服务host:"+port+",service_id:"+hostname+"ss:"+ss;
    }

    /**
     * * 用于测试GET请求
     * @return json
     */
    //@RequestMapping("/getTest")
    @GetMapping(value = "/getTest")
    public String getTest(@RequestParam Map<String, Object> map){
        logger.info("服务host:"+port+",service_id:"+hostname);
        return "这是一个服务提供者,name："+map.get("name")+";password:"+"";
    }

    /**
     * * 用于测试POST请求
     * @return json
     */
    @RequestMapping(value = "/postTest",method =RequestMethod.POST)
    public String postTest(@RequestBody User user ){
        return "这是一个服务提供者,name:"+user.getName()+";password:"+"";
    }

    /**
     * * 用于测试PUT请求
     * @param name 名称
     * @param password 密码
     * @return json
     */
    @RequestMapping("/putTest")
    public String putTest(String name,String password){
        logger.info("服务host:"+port+",service_id:"+hostname);
        return "这是一个服务提供者,name"+name+";password:"+password+"";
    }

    /**
     * * 用于测试DELETE请求
     * @param name 名称
     * @param password 密码
     * @return json
     */
    @RequestMapping("/deleteTest")
    public String deleteTest(String name,String password){
        logger.info("服务host:"+port+",service_id:"+hostname);
        return "这是一个服务提供者,name"+name+";password:"+password+"";
    }



    @RequestMapping("/findAll")
    public  List<User> findAll(String ids){
        logger.info("服务host:"+port+",service_id:"+hostname+"ss:"+ss);
        System.out.println("port:"+port);
        User user;
        ArrayList<User> list = new ArrayList();
        for(int i=0;i<=2;i++){
            user  = new User();
            user.setName("name"+i);
            user.setPassword("pw"+i);
            list.add(user);
        }
        return list;
    }
}
