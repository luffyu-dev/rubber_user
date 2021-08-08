package com.rubber.user.web.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/info")
    public String getTest(){
        return "hello";
    }
}
