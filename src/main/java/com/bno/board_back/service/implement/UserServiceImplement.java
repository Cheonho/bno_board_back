package com.bno.board_back.service.implement;

import com.bno.board_back.config.Hmac.SecretKeyConfig;
import com.bno.board_back.dto.responseDto.GetUserCheckEmailDto;
import com.bno.board_back.dto.responseDto.GetUserCheckNicknameDto;
import com.bno.board_back.dto.responseDto.GetUserJoinResponseDto;
import com.bno.board_back.dto.responseDto.GetUserLoginResponseDto;
import com.bno.board_back.dto.userDto.JoinRequestDto;
import com.bno.board_back.dto.userDto.LoginRequestDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import com.bno.board_back.repository.UserRepository;
import com.bno.board_back.service.UserService;
import com.bno.board_back.utils.TsidUtilUseSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final SecretKeyConfig secretKeyConfig;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    @Override
    public ResponseEntity<? super GetUserLoginResponseDto> loginPage(LoginRequestDto loginRequestDto) {

        // 1. 이메일로 사용자 조회
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(loginRequestDto.getEmail());
        if (optionalUserEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.duplicateEmail());

        }
        UserEntity userEntity = optionalUserEntity.get();

        // 2. 비밀번호 검증
        boolean passwordMatches = bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), userEntity.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.notFoundPassword());
        }

        // 3. 로그인 성공 시, 응답 반환
        return GetUserLoginResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserJoinResponseDto> joinPage(JoinRequestDto joinRequestDto, BindingResult bindingResult) {

        // 1. 이메일 또는 닉네임이 이미 존재하는지 확인
        Optional<UserEntity> existUserByEmail = userRepository.findByEmail(joinRequestDto.getEmail());
        Optional<UserEntity> existUserByNickname = userRepository.findByUserNickname(joinRequestDto.getUserNickname());

        if (existUserByEmail.isPresent() || existUserByNickname.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateUser());
        }

        // 유효성 검사 결과가 있을 때
        if (bindingResult.hasErrors()) {
            // 오류 메시지를 수집
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.append(error.getDefaultMessage()).append(" ");
            }

            // 오류 메시지와 함께 BAD_REQUEST 상태 반환
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        // 2. 새로운 UserEntity 생성
        UserEntity userEntity = UserEntity.builder()
                .id(TsidUtilUseSystem.getTsid())
                .email(joinRequestDto.getEmail())
                .userNickname(joinRequestDto.getUserNickname())
                .password(bCryptPasswordEncoder.encode(joinRequestDto.getPassword())) // 비밀번호 암호화
                .address(joinRequestDto.getAddress())
                .role(joinRequestDto.getRole() != null ? joinRequestDto.getRole() : "USER")
                .build();

        // 3. 저장
        userRepository.save(userEntity);

        return GetUserJoinResponseDto.success(userEntity);
    } ;

    @Override
    public ResponseEntity<? super GetUserCheckEmailDto> checkEmail(String email) {
        boolean isAvailable = userRepository.existsByEmail(email) ;
        if(isAvailable) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateEmail()) ;
        }
        return GetUserCheckEmailDto.usedemail();
    }

    @Override
    public ResponseEntity<? super GetUserCheckNicknameDto> checkNickname(String userNickname) {
        boolean isAvailable = userRepository.existsByUserNickname(userNickname) ;
        if(isAvailable) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateNickname()) ;
        }
        return GetUserCheckEmailDto.usednickname();
    }
}


