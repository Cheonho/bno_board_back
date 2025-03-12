package com.bno.board_back.service.implement;

import com.bno.board_back.service.OtpService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImplement implements OtpService {

    private final GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

    // 시크릿키 발급
    @Override
    public String generateSecretKey() {
        GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        return key.getKey();
    }

    // 사용자가 입력한 코드와 OTP 코드가 일치하는지 검증
    @Override
    public boolean verifyOtp(String secretKey, int otpCode) {
        return googleAuthenticator.authorize(secretKey, otpCode);
    }
}
