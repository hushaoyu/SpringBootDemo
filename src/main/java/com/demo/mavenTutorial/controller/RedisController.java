package com.demo.mavenTutorial.controller;/**
 * @author shaoy
 * @date 2019/3/26 9:48
 */

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisController
 * @Description redis cache
 * @Version 1.0
 **/
@Controller
@RequestMapping("/cache")
public class RedisController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/str", method = RequestMethod.GET)
    @ResponseBody
    public String env(@RequestParam String value) {
        redisTemplate.opsForValue().set("key2", "hello world", 10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("key2"));
        redisTemplate.opsForValue().set("key2", value, 6);
        System.out.println(redisTemplate.opsForValue().get("key2"));
        return redisTemplate.opsForValue().get("key2");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getCacheList(@RequestParam String key) {
        redisTemplate.opsForList().leftPush(key, "hello,world");
        System.out.println(redisTemplate.opsForList().range(key, 0, -1));
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @RequestMapping(value = "/if", method = RequestMethod.GET)
    @ResponseBody
    public boolean setCacheIfAbsent() {
        return redisTemplate.opsForValue().setIfAbsent("key1", "test");
    }
}
