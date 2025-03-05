package com.bno.board_back.config.Hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HmacUtil {

    // HMAC-SHA256으로 비밀번호 해싱
    public static String hmacSHA256(String password, String secretKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] hash = mac.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(hash); // Base64로 인코딩
        } catch (Exception e) {
            throw new RuntimeException("에러", e);
        }
    }
}