package com.luyuze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomLoginController {

    @RequestMapping("/login/page")
    public String toLogin() {
        return "login"; // classpath: /templates/login.html
    }
}
