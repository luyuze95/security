package com.luyuze.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        // 获取登陆用户的信息第一种方式：
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            map.put("username", username);
        }
        return "index"; // resources/templates/index.html
    }

    // 获取登陆用户的信息第二种方式
    @ResponseBody
    @RequestMapping("/user/info")
    public Object userInfo(Authentication authentication) {
        return authentication.getPrincipal();
    }

    // 获取登陆用户的信息第三种方式
    @RequestMapping("/user/info2")
    @ResponseBody
    public Object userInfo2(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
