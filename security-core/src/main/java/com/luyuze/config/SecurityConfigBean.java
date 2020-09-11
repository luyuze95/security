package com.luyuze.config;

import com.luyuze.authentication.mobile.SmsCodeSender;
import com.luyuze.authentication.mobile.SmsSend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主要为容器中添加Bean实例
 */
@Configuration
public class SecurityConfigBean {

    /**
     * 如果容器中没有其它的SmsSend的实例，默认就是使用SmsCodeSender的实例
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsSend.class)
    public SmsSend smsSend() {
        return new SmsCodeSender();
    }
}
