package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author zhoulk
 *  @date: 2019/1/3
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/login")
    public String login(){
        return "zlwwk";
    }
}
