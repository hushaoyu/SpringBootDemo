package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.service.SayHelloService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class HelloWroldController {
    @Autowired
    SayHelloService sayHelloService;

    @ApiOperation(value = "打印字符串")
    @RequestMapping(value = "/sayHe", method = RequestMethod.GET)
    public @ResponseBody String sayHello(@RequestParam String name) {
        return sayHelloService.sayHello(name);
    }
}
