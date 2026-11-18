package com.example.iot.actiondispatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(@Value("${DASHBOARD_ORIGIN:}") String dashboardOrigin) {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                CorsRegistration decisions = registry.addMapping("/decide")
                        .allowedOriginPatterns("null", "http://localhost:*", "http://127.0.0.1:*")
                        .allowedMethods("POST", "OPTIONS")
                        .allowedHeaders("Content-Type");
                if (!dashboardOrigin.isBlank()) {
                    decisions.allowedOrigins(dashboardOrigin.trim());
                }
            }
        };
    }
}
