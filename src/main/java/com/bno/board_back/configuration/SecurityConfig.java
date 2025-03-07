package com.bno.board_back.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless 세션 설정

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/join",
                                "/idcheck/**", "/namecheck/**" ,
                                "/nicknamecorrection/**", "/board/board-list",
                                "/search-list/**", "detailBoard",
                                "*/view", "/swagger-ui/**", "/v3/api-docs/**",
                                "/**"
                        ).permitAll() // 특정 URL에 대한 접근 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                );


        return http.build();
    }
}

