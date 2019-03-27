package com.demo.mavenTutorial.service.serviceImpl;

import com.demo.mavenTutorial.service.WorldGetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "city")
public class WroldGetUserServiceImpl implements WorldGetUserService {
    @Autowired
    @Qualifier("dataSourceTemplate")
    JdbcTemplate jdbcTemplate;
    @Override
    @Cacheable("'getUser'")
    public Integer getUser() {
        Integer rowCount = jdbcTemplate.queryForObject("select count(*) from city", Integer.class);
        return rowCount;
    }

    @CacheEvict(key = "#name")
    @Override
    public boolean clearCache(String name) {
        System.out.println("clear cache");
        return true;
    }

    @Override
    public Integer getCountByCountryCode(String code) {
        Integer rowCont = jdbcTemplate.queryForObject("select count(*) from city where city.CountryCode = ?", Integer.class, code);
        return rowCont;
    }

    @Autowired
    @Qualifier("dev-dataSourceTemplate")
    JdbcTemplate devJdbcTemplate;
    @Override
    public Integer getMysqlDBCount() {
        Integer rowCount = devJdbcTemplate.queryForObject("select count(*) from db", Integer.class);
        return rowCount;
    }
}
