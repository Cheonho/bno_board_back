package com.bno.board_back.service;

import com.bno.board_back.dto.responseDto.*;
import com.bno.board_back.dto.userDto.JoinRequestDto;
import com.bno.board_back.dto.userDto.LoginRequestDto;
import com.bno.board_back.dto.userDto.OtpVerifyRequestDto;
import com.bno.board_back.dto.userDto.UserInformationChangeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface UserService {
     ResponseEntity<? super GetUserLoginResponseDto> loginPage(LoginRequestDto loginRequestDto) ;
     ResponseEntity<? super GetUserJoinResponseDto> joinPage(JoinRequestDto joinRequestDto, BindingResult bindingResult);
     ResponseEntity<? super GetUserCheckEmailDto> checkEmail(String email) ;
     ResponseEntity<? super GetUserCheckNicknameDto> checkNickname(String userNickname) ;
     ResponseEntity<? super GetUserInformationChangeDto> changeNickname(UserInformationChangeDto userInformationChangeDto) ;
     ResponseEntity<? super GetUserInformationChangeDto> changePassword(UserInformationChangeDto userInformationChangeDto, BindingResult bindingResult) ;
     ResponseEntity<? super GetUserInformationChangeDto> changeAddress(UserInformationChangeDto userInformationChangeDto) ;
     ResponseEntity<? super GetUserApiTokenDto> apitokendata(String authorizationHeader) ;

     ResponseEntity<? super GetUserLoginResponseDto> otpLoginPage(OtpVerifyRequestDto otpVerifyRequestDto);
}