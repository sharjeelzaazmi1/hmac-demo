package com.example.hmacdemo.config;

import com.example.hmacdemo.security.HmacAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HmacAuthenticationFilter> loggingFilter() {
        FilterRegistrationBean<HmacAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

        // Register your filter class
        registrationBean.setFilter(new HmacAuthenticationFilter());

        // Specify URL patterns for which the filter should be applied
        registrationBean.addUrlPatterns("/protected-resource"); // apply to specific endpoint

        // Optionally, you can set the order (lower values are higher priority)
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
