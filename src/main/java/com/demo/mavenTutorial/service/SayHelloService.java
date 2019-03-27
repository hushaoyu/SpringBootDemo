package com.demo.mavenTutorial.service;

import org.springframework.stereotype.Service;

@Service
public interface SayHelloService {
    /**
     * @Author chengpunan
     * @Description //TODO shaoy
     * @Date 18:07 2019/3/25
     * @Param name 传入的名称
     * @return java.lang.String
     **/
    String sayHello(String name);
}