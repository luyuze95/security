package com.luyuze.authentication.mobile;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

/**
 * 用于组合其它关于手机登陆的组件
 */
@Component
public class MobileAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
//        mobileAuthenticationFilter.setAuthenticationManager();
    }
}
