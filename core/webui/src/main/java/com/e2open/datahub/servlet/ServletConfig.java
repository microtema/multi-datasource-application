package com.e2open.datahub.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean interconnectServletRegistrationBean () {
        return new ServletRegistrationBean (new LoginFormServlet (), "/form");
    }
}
