package com.bno.board_back.configuration;

import com.bno.board_back.filter.JwtAuthenticationFilter;
import com.bno.board_back.provider.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless 세션 설정
//                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/**", "/join", "/otp/**",
                                "/idcheck/**", "/namecheck/**" ,
                                "/nicknamecorrection/**", "/addresscorrection/**",
                                "/passwordcorrection/**",
                                "/mypage/**",
                                "/board/search-list/**", "/board/detail/*",
                                "/board/*/view", "/swagger-ui/**", "/swagger-ui/*", "/v3/api-docs/**", "/v3/api-docs/*",
                                "/board/board-list", "/board/*/comment", "/board/*/comment/**", "/file/refresh-url/*"
//                                "/**", "/*"
                        ).permitAll() // 특정 URL에 대한 접근 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

