package com.demo.mavenTutorial.service;

import org.springframework.stereotype.Service;

@Service
public interface WorldGetUserService {
    /**
     * @Author chengpunan
     * @Description //TODO shaoy
     * @Date 18:07 2019/3/25
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getUser();

    /**
     * @Author chengpunan
     * @Description //TODO shaoy
     * @Date 18:07 2019/3/25
     * @Param [code]
     * @return java.lang.Integer
     **/
    Integer getCountByCountryCode(String code);

    /**
     * @Author chengpunan
     * @Description //TODO shaoy
     * @Date 18:07 2019/3/25
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getMysqlDBCount();

    /**
     * @Author chengpunan
     * @Description //TODO shaoy
     * @Date 18:07 2019/3/25
     * @Param [name]
     * @return boolean
     **/
    boolean clearCache(String name);
}
