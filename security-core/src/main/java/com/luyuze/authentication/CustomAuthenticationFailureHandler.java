package com.luyuze.authentication;

import com.luyuze.properties.LoginResponseType;
import com.luyuze.properties.SecurityProperties;
import com.luyuze.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理失败认证的
 */
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    SecurityProperties securityProperties;

    /**
     *
     * @param e 认证失败时抛出异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        if (LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())) {
            // 认证失败响应JSON字符串
            CommonResult result = CommonResult.build(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(result.toJsonString());
        } else {
            super.setDefaultFailureUrl(securityProperties.getAuthentication().getLoginPage() + "?error");
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }
    }
}
