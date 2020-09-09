package com.luyuze.config;

import com.luyuze.authentication.code.ImageCodeValidateFilter;
import com.luyuze.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity // 开启SpringSecurity过滤器链
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDetailsService customUserDetailsService;

    // 配置文件参数
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private ImageCodeValidateFilter imageCodeValidateFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 明文 + 随机盐值  加密存储
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     * 1、认证信息（用户名，密码）
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String password = passwordEncoder().encode("1234");
//        logger.info("加密之后存储的密码：" + password);
//        // 数据库存储的密码必须是加密后的，不然会报错 : There is no PasswordEncoder mapped for the id "null"
//        auth.inMemoryAuthentication()
//                .withUser("luyuze")
//                .password(password)
//                .authorities("ADMIN");
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * 资源权限配置：
     * 1、被拦截的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()  // 采用httpBasic认证方式
        http.addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登陆方式
                .loginPage(securityProperties.getAuthentication().getLoginPage())
                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl()) // 登陆表单提交处理url，默认是/login
                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter())  // 默认的是username
                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())  // 默认的是password
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .authorizeRequests()  // 认证请求
                .antMatchers(securityProperties.getAuthentication().getLoginPage(),
                        "/code/image").permitAll() // 放行/login/page 不需要认证可访问
                .anyRequest().authenticated()  // 所有访问该应用的http请求都要通过身份认证才可以访问
        ;
    }

    /**
     * 一般是针对静态资源放行
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/dist/**", "/modules/**", "/plugins/**");
    }
}
