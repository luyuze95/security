package com.luyuze.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 手机认证处理提供者
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {
    /**
     * 认证处理：
     * 1、通过手机号码查询用户信息（UserDetailsService实现）
     * 2、当查询到用户信息，则认为认证通过，封装Authentication对象
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    /**
     * 通过这个方法，来选择对应的Provider，即选择MobileAuthenticationProvider
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return MobileAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
