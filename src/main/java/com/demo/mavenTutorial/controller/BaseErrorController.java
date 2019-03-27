package com.demo.mavenTutorial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public abstract class BaseErrorController extends AbstractErrorController {
    Log log =  LogFactory.getLog(AbstractErrorController.class);
    private String uriFix = ".json";

    @Autowired
    ObjectMapper objectMapper;

    public BaseErrorController() {
        super(new DefaultErrorAttributes());
    }

    @RequestMapping("/error")
    public ModelAndView getErrorPath(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
        Throwable cause = getCause(request);
        int status = (Integer)model.get("status");
        String message = (String)model.get("message");
        String errorMessage = getErrorMessage(cause);

        log.info(status + "," + message, cause);
        response.setStatus(status);
        if (!isJsonRequest(request)) {
            ModelAndView modelAndView = new ModelAndView("/beetl/error.html");
            modelAndView.addAllObjects(model);
            modelAndView.addObject("errorMessage", errorMessage);
            modelAndView.addObject("status", status);
            modelAndView.addObject("cause", cause);
            return modelAndView;
        } else {
            Map error = new HashMap() ;
            error.put ("success", false);
            error.put ("errorMessage", errorMessage);
            error.put ("message", message);
            log.error(error);
            return null;
        }
    }

    protected Throwable getCause(HttpServletRequest request) {
        Throwable error = (Throwable)request.getAttribute("javax.servlet.error.exception");
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException) error).getCause();
            }
        }
        return error;
    }

    protected String getErrorMessage(Throwable ex) {
        return "服务器内部错误，请联系管理员.";
    }

    protected boolean isJsonRequest(HttpServletRequest request) {
        String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri != null && requestUri.endsWith(uriFix)) {
            return true;
        } else {
            return false;
        }
    }
}
