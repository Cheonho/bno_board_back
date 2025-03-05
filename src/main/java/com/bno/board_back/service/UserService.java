package com.bno.board_back.service;

import com.bno.board_back.dto.responseDto.GetUserCheckEmailDto;
import com.bno.board_back.dto.responseDto.GetUserCheckNicknameDto;
import com.bno.board_back.dto.responseDto.GetUserJoinResponseDto;
import com.bno.board_back.dto.responseDto.GetUserLoginResponseDto;
import com.bno.board_back.dto.userDto.JoinRequestDto;
import com.bno.board_back.dto.userDto.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface UserService {
     ResponseEntity<? super GetUserLoginResponseDto> loginPage(LoginRequestDto loginRequestDto) ;
     ResponseEntity<? super GetUserJoinResponseDto> joinPage(JoinRequestDto joinRequestDto, BindingResult bindingResult);
     ResponseEntity<? super GetUserCheckEmailDto> checkEmail(String email) ;
     ResponseEntity<? super GetUserCheckNicknameDto> checkNickname(String userNickname) ;
     }