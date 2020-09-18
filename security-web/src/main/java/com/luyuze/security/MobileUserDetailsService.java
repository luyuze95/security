package com.luyuze.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 通过手机号码获取用户信息和权限资源
 */
@Component
public class MobileUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        logger.info("请求的手机号码是：" + mobile);
        // 1、通过手机号查询用户信息
        // 2、如果有用户信息，则再获取权限资源
        // 3、封装用户信息
        return new User(mobile, "",
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
    }
}
