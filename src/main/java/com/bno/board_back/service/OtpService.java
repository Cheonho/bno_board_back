package com.bno.board_back.service;

import com.bno.board_back.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface OtpService {

    String generateSecretKey();
    boolean verifyOtp(String secretKey, int otpCode);


}
