package com.luyuze.controller;

import com.luyuze.authentication.mobile.SmsSend;
import com.luyuze.result.CommonResult;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 关于手机登陆控制层
 */
@Controller
public class MobileLoginController {

    public static final String SESSION_KEY = "SESSION_KEY_MOBILE_CODE";

    @Autowired
    private SmsSend smsSend;

    /**
     * 前往手机验证码登陆页
     * @return
     */
    @RequestMapping("/mobile/page")
    public String toMobilePage() {
        return "login-mobile";
    }

    /**
     * 发送手机验证码
     * @return
     */
    @ResponseBody // 响应json字符串
    @RequestMapping("/code/mobile")
    public CommonResult smsCode(HttpServletRequest request) {
        // 1、生成一个手机验证码
        String code = RandomStringUtils.randomNumeric(4);
        // 2、将手机验证码保存到session中
        request.getSession().setAttribute(SESSION_KEY, code);
        // 3、发送验证码到用户手机上
        String mobile = request.getParameter("mobile");
        smsSend.sendSms(mobile, code);
        return CommonResult.ok();
    }
}
