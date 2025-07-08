package com.pts.adm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho tất cả API
                // .allowedOrigins("http://localhost:4200", "https://your-frontend.com") // Cụ thể origin
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                 .exposedHeaders("access-token") // Nếu muốn client đọc header tùy chỉnh
                 .allowCredentials(true) // Cho phép gửi cookie, token
                .maxAge(3600); // Cache preflight 1h
    }
}
