package com.bno.board_back.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class OtpVerifyRequestDto {
    private String email;
    private String otpCode;
}
