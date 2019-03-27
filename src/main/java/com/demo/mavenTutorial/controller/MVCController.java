package com.demo.mavenTutorial.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.mavenTutorial.domain.User;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
//@RestController
@RequestMapping("/mvc")
public class MVCController {
    @RequestMapping("/thymeleaf/index")
    public String useModel(Model model) {
        model.addAttribute("name","hello,world");
        return "/thymeleaf/index";
    }

    /* produces */
    @RequestMapping(value = "/produces", produces = {"application/json"})
    public @ResponseBody JSONObject responseJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1", "value1");
        jsonObject.put("key2", "value2");
        return jsonObject;
    }

    /* consumes */
    @RequestMapping(value = "/consumes", consumes = "application/json", method = RequestMethod.POST)
    public @ResponseBody JSONObject requestJSON(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        return jsonObject;
    }

    /* @PathVariable */
    @RequestMapping(value = "/pathVariable/{id}")
    public @ResponseBody String usePathVariable(@PathVariable Integer id) {
        return "PathVariable is id = " + id;
    }

    /* multipartFile */
    @PostMapping(value = "multipartFile")
    public @ResponseBody String useMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName;
    }

    /* multipartFile files */
    @PostMapping(value = "multipartFiles")
    public @ResponseBody
    Integer useMultipartFiles(MultipartFile[] files) {
//        StringArray stringArray = new StringArray();
        Integer len = files.length;
        return len;
    }

    /* @ModelAttribute */
//    @ModelAttribute
    public void useModelAttribute(@RequestParam Integer id, Model model) {
        model.addAttribute("userId", id);
    }
    @GetMapping(value = "/thymeleaf/modelAttribute")
    public String getUserId(Model model) {
        System.out.println(model.containsAttribute("userId"));
        return "/thymeleaf/success";
    }

    /* @InitBinder */
    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }
    @ResponseBody
    @RequestMapping(value = "initBinder", method = RequestMethod.GET)
    public void useInitBinder(@RequestParam Date date) {
        System.out.println(date);
        return;
    }

    /* 自定义拦截器 */
    @GetMapping("/interceptor")
    @ResponseBody
    public String useInterceptor(HttpServletRequest request) {
       String headers =  request.getHeader("private");
       System.out.println(headers);
       return headers;
    }

    /* 跨域请求 */
    @RequestMapping(value = "/cors", method = RequestMethod.GET)
    @ResponseBody
    public String useCors(HttpServletRequest request) {
        String uri =  request.getRequestURI();
        System.out.println(uri);
        return uri;
    }

    @RequestMapping(value = "/freemarker/index", method = RequestMethod.POST)
    public ModelAndView useFreemarker(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/freemarker/index");

        return modelAndView;
    }
}
