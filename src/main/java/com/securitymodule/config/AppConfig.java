package com.securitymodule.config;

import com.securitymodule.filter.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private RequestFilter requestFilter;

    @Bean
    public FilterRegistrationBean<RequestFilter> filterRegistrationBean() {
        FilterRegistrationBean < RequestFilter > registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(requestFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
