package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.domain.ConfigBean;
import com.demo.mavenTutorial.domain.DevConfigBean;
import com.demo.mavenTutorial.service.WorldGetUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
@EnableConfigurationProperties({ConfigBean.class, DevConfigBean.class})
public class DBController {
    @Value("${my.name}")
    String envName;
    @Value("${my.date}")
    String envDate;

    @Autowired
    ConfigBean configBean;
    @Autowired
    DevConfigBean devConfigBean;

    @Autowired
    WorldGetUserService worldGetUserService;

    @ApiOperation(value = "获取city表数据")
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public Integer getCity() {
        return worldGetUserService.getUser();
    }

    @ApiOperation(value = "通过countryCode获取city表行数")
    @RequestMapping(value = "/getRowCountByCountryCode", method = RequestMethod.GET)
    public Integer getCountByCountryCode(@RequestParam(required = true) String code) {
        return worldGetUserService.getCountByCountryCode(code);
    }

    @ApiOperation(value = "获取city表行数")
    @RequestMapping(value = "/mysql/getCount", method = RequestMethod.GET)
    public Integer mysqlGetCount() {
        return worldGetUserService.getMysqlDBCount();
    }

    @ApiOperation(value = "获取环境变量配置")
    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public String getEnv() {
        return configBean.toString();
    }

    @ApiOperation(value = "获取dev环境的环境变量配置")
    @RequestMapping(value = "/env/dev", method = RequestMethod.GET)
    public String getEnvDev() {
        return devConfigBean.toString();
    }

    @ApiOperation(value = "清除缓存")
    @RequestMapping(value = "/cache/clear", method = RequestMethod.GET)
    public boolean clearCache(@RequestParam String name) {
        return worldGetUserService.clearCache(name);
    }
}
