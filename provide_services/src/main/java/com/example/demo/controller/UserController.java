package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/login")
    public String login(){
        logger.info("服务host:"+port+",service_id:"+hostname);
        return "这是一个服务提供者！";
    }
}
