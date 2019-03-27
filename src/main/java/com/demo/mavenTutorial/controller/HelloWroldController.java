package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class HelloWroldController {
    @Autowired
    SayHelloService sayHelloService;

    @RequestMapping("/sayHe")
    public @ResponseBody String sayHello(@RequestParam String name) {
        return sayHelloService.sayHello(name);
    }
}
