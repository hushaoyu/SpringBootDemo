package com.demo.mavenTutorial.service.serviceImpl;

import com.demo.mavenTutorial.service.SayHelloService;
import org.springframework.stereotype.Service;

@Service
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public String sayHello(String name) {
        return name;
    }
}
