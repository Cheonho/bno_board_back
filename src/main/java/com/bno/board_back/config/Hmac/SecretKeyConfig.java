package com.bno.board_back.config.Hmac;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SecretKeyConfig {

    @Value("${jwt.secret}") // application.properties에서 가져옴
    private String secretKey;
}