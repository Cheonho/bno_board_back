package com.bno.board_back.provider.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Component

public class RefreshTokenProvider {

    private final SecretKey secretKey;
    private final long expiredMillis = 7 * 24 * 60 * 60 * 1000;

    public RefreshTokenProvider(@Value("${jwt.secret}") String key) {
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }


    // 토큰 생성
    public String createRefreshToken(String id, String email) {
        // 현재 시간 가져오기
        Date iatDate = new Date();

        // 만료 시간 설정
        Calendar exPCalendar = Calendar.getInstance();
        exPCalendar.setTime(iatDate);
        exPCalendar.add(Calendar.MILLISECOND, (int) expiredMillis);

        Date expDate = exPCalendar.getTime();

        return Jwts.builder()
                .claim("id", id)
                .claim("email", email)
                .setIssuedAt(iatDate)
                .setExpiration(expDate)
                .signWith(secretKey)
                .compact();
    }

    public String getId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("id", String.class);
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("email", String.class);
    }

    // 토큰 검증 - 토큰 유효기간 비교
    public Boolean isExpired(String token) {
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expDate = claims.getExpiration();

            // 현재 날짜가 exp 날짜보다 뒤에 있으면, 만료됨
            return new Date().after(expDate);

        } catch (ExpiredJwtException e){
            e.printStackTrace();
            return true;
        }

    }
}
