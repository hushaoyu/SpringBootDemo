package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.domain.ConfigBean;
import com.demo.mavenTutorial.domain.DevConfigBean;
import com.demo.mavenTutorial.service.WorldGetUserService;
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
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public Integer getCity() {
        return worldGetUserService.getUser();
    }

    @RequestMapping(value = "/getRowCountByCountryCode", method = RequestMethod.GET)
    public Integer getCountByCountryCode(@RequestParam(required = true) String code) {
        return worldGetUserService.getCountByCountryCode(code);
    }

    @RequestMapping(value = "/mysql/getCount", method = RequestMethod.GET)
    public Integer mysqlGetCount() {
        return worldGetUserService.getMysqlDBCount();
    }

    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public String getEnv() {
        return configBean.toString();
    }

    @RequestMapping("/env/dev")
    public String getEnvDev() {
        return devConfigBean.toString();
    }

    @RequestMapping("/cache/clear")
    public boolean clearCache(@RequestParam String name) {
        return worldGetUserService.clearCache(name);
    }
}
