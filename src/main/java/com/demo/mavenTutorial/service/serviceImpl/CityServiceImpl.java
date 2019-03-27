package com.demo.mavenTutorial.service.serviceImpl;

import com.demo.mavenTutorial.domain.City;
import com.demo.mavenTutorial.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl {
    @Autowired
    CityService cityService;
    public City findByCityName(String name) {
        return cityService.findByName(name);
    }
}
