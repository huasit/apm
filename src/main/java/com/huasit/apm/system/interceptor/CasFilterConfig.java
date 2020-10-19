package com.huasit.apm.system.interceptor;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class CasFilterConfig {

    /**
     *
     */
    @Value("${cas.server.url}")
    private String serverUrl;

    /**
     *
     */
    @Value("${cas.server.name}")
    private String serverName;

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean listenerRegistrationBean = new ServletListenerRegistrationBean();
        listenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        listenerRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return listenerRegistrationBean;
    }

    /**
     * 单点登录退出
     *
     */
    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new SingleSignOutFilter());
        registrationBean.addUrlPatterns("/third-party/*");
        registrationBean.addInitParameter("casServerUrlPrefix", serverUrl);
        registrationBean.setName("CAS Single Sign Out Filter");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    /**
     * 单点登录认证
     *
     */
    @Bean
    public FilterRegistrationBean AuthenticationFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/third-party/*");
        registrationBean.setName("CAS Filter");
        registrationBean.addInitParameter("casServerLoginUrl", serverUrl);
        registrationBean.addInitParameter("serverName", serverName);
        registrationBean.setOrder(3);
        return registrationBean;
    }

    /**
     * 单点登录校验
     *
     */
    @Bean
    public FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        registrationBean.addUrlPatterns("/third-party/*");
        registrationBean.setName("CAS Validation Filter");
        registrationBean.addInitParameter("casServerUrlPrefix", serverUrl);
        registrationBean.addInitParameter("serverName", serverName);
        registrationBean.setOrder(4);
        return registrationBean;
    }

    /**
     * 单点登录请求包装
     *
     */
    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpServletRequestWrapperFilter());
        registrationBean.addUrlPatterns("/third-party/*");
        registrationBean.setName("CAS HttpServletRequest Wrapper Filter");
        registrationBean.setOrder(5);
        return registrationBean;
    }

    /**
     * 单点登录本地用户信息
     *
     * @return
     @Bean public FilterRegistrationBean localUserInfoFilter() {
     FilterRegistrationBean registrationBean = new FilterRegistrationBean();
     registrationBean.setFilter(new LocalUserInfoFilter());
     registrationBean.addUrlPatterns("/third-party-callback**");
     registrationBean.setName("localUserInfoFilter");
     registrationBean.setOrder(6);
     return registrationBean;
     }*/
}
