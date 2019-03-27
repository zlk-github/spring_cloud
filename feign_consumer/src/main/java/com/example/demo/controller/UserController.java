package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Administrator zhoulk
 * date: 2019/3/27 0027
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(){
        return  userService.login();
    }
}
