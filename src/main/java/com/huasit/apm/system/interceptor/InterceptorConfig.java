package com.huasit.apm.system.interceptor;

import com.huasit.apm.system.util.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleUtil.init(messageSource);
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

    /**
     *
     */
    @Autowired
    Interceptor interceptor;

    /**
     *
     */
    @Autowired
    MessageSource messageSource;
}