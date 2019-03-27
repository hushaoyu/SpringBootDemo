package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.domain.City;
import com.demo.mavenTutorial.service.serviceImpl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    CityServiceImpl cityServiceImpl;
    @RequestMapping("/city/findByName")
    public City findByName(@RequestParam String name) {
        return cityServiceImpl.findByCityName(name);
    }
}
