package com.msyhu.naekaracubae.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:9090", "http://naekaracubae.msyhu.com:80", "http://home.msyhu.com:9090");
                // cors 다 열어놓음. 추후 수정 필요!
                .allowedOrigins("*");

    }
}
