package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.domain.City;
import com.demo.mavenTutorial.service.serviceImpl.CityServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    CityServiceImpl cityServiceImpl;

    @ApiOperation(value = "根据Name字段获取city表数据")
    @RequestMapping(value = "/city/findByName", method = RequestMethod.GET)
    public City findByName(@RequestParam String name) {
        return cityServiceImpl.findByCityName(name);
    }
}
