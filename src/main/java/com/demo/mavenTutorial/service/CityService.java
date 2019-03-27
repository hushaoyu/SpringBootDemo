package com.demo.mavenTutorial.service;

import com.demo.mavenTutorial.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CityService extends JpaRepository<City, Long> {
    /**
     * @Author chengpunan
     * @Description //TODO shaoy
     * @Date 18:07 2019/3/25
     * @Param [name]
     * @return com.demo.mavenTutorial.domain.City
     **/
    City findByName(String name);
}
