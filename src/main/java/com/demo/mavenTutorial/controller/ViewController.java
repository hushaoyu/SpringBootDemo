package com.demo.mavenTutorial.controller;

import com.demo.mavenTutorial.conf.EnvConfig;
import com.demo.mavenTutorial.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView indexView(@RequestBody User user) {
        ModelAndView view = new ModelAndView("/beetl/index.html");
        view.addObject("user", user);
        return view;
    }
    
    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public Integer getEnv() {
        EnvConfig envConfig = null;
        return envConfig.getPort();
    }
}
