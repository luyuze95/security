package com.luyuze.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "luyuze.security")
@Component
public class SecurityProperties {

    // 会将luyuze.security.authentication下的属性绑定到AuthenticationProperties对象上
    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}
