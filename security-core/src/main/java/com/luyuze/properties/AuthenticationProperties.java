package com.luyuze.properties;

import lombok.Data;

@Data
public class AuthenticationProperties {

    private String loginPage = "/login/page";

    private String loginProcessingUrl = "/login/form";

    private String usernameParameter = "name";

    private String passwordParameter = "pwd";

    private String[] staticPaths = {"/dist/**", "/modules/**", "/plugins/**"};

    // 认证响应的类型  JSON、REDIRECT
    private LoginResponseType loginType = LoginResponseType.REDIRECT;

    private String imageCodeUrl = "/code/image";

    private String mobileCodeUrl = "/code/mobile";

    private String mobilePage = "/mobile/page";

    private Integer tokenValiditySeconds = 604800;

}
