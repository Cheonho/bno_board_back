package com.bno.board_back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 요청에 대해 CORS 허용
                .allowedOrigins("*")  // React 앱에서만 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // 허용할 HTTP 메소드
                .allowedHeaders("*")  // 허용할 헤더
                .exposedHeaders("X-Total-Page", "X-Total-Elements", "X-Page-Number", "X-Page-Size",
                        "X-Current-Section", "X-First-Page-Number", "X-Last-Page-Number");
    }
}