package com.bno.board_back.controller;

import com.bno.board_back.dto.responseDto.*;
import com.bno.board_back.dto.userDto.*;
import com.bno.board_back.entity.UserEntity;
import com.bno.board_back.provider.jwt.JwtTokenProvider;
import com.bno.board_back.repository.UserRepository;
import com.bno.board_back.service.OtpService;
import com.bno.board_back.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final OtpService otpService;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, OtpService otpService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.otpService = otpService;
    }

    @PostMapping("/login")
    public ResponseEntity<? super GetUserLoginResponseDto> LoginPage(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        ResponseEntity<? super GetUserLoginResponseDto> response = userService.loginPage(loginRequestDto);
        session.setAttribute("loginDto", response);
        return response;
    }


    @PostMapping("/join")
    public ResponseEntity<? super GetUserJoinResponseDto> JoinPage(@RequestBody @Valid JoinRequestDto joinRequestDto, BindingResult bindingResult) {
        ResponseEntity<? super GetUserJoinResponseDto> response = userService.joinPage(joinRequestDto, bindingResult);
        return response;
    }


    @GetMapping("/idcheck")
    public ResponseEntity<? super GetUserCheckEmailDto> EmailCheckPage(@RequestParam String email) {
        ResponseEntity<? super GetUserCheckEmailDto> response = userService.checkEmail(email);
        return response;
    }

    @GetMapping("/namecheck")
    public ResponseEntity<? super GetUserCheckNicknameDto> NameCheckPage(@RequestParam String userNickname) {
        ResponseEntity<? super GetUserCheckNicknameDto> response = userService.checkNickname(userNickname);
        return response;
    }


    @PostMapping("/nicknamecorrection")
    public ResponseEntity<? super GetUserInformationChangeDto> Nicknamecorrection(@RequestBody @Valid UserInformationChangeDto userInformationChangeDto) {
        ResponseEntity<? super GetUserInformationChangeDto> response = userService.changeNickname(userInformationChangeDto);
        return response;
    }

    @PostMapping("/passwordcorrection")
    public ResponseEntity<? super GetUserInformationChangeDto> Passwordcorrection(@RequestBody @Valid UserInformationChangeDto userInformationChangeDto, BindingResult bindingResult) {
        ResponseEntity<? super GetUserInformationChangeDto> response = userService.changePassword(userInformationChangeDto, bindingResult);
        return response;
    }

    @PostMapping("/addresscorrection")
    public ResponseEntity<? super GetUserInformationChangeDto> Addresscorrection(@RequestBody @Valid UserInformationChangeDto userInformationChangeDto) {
        ResponseEntity<? super GetUserInformationChangeDto> response = userService.changeAddress(userInformationChangeDto);
        return response;
    }

    @PostMapping("/mypage/apitokendata")
    public ResponseEntity<? super GetUserApiTokenDto> ApiTokenData(@RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<? super GetUserApiTokenDto> response = userService.apitokendata(authorizationHeader);
        return response;
    }


    @Transactional
    @PostMapping("/otp/setup")
    public ResponseEntity<? super OtpSetupResponseDto> setupOtp(@RequestHeader("Authorization") String authorizationHeader) {
        //  JWT 토큰에서 사용자 ID 추출
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtTokenProvider.getUserPk(token); // 토큰에서 사용자 ID 가져오기

        //  유저 조회
        UserEntity user = userRepository.findById(userId).orElse(null);

        //  유저가 존재하지 않는 경우
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new OtpSetupResponseDto(null));
        }

        //  이미 OTP가 설정된 경우
        if (user.getOtpSecretKey() != null) {
            String otpAuthUrl = String.format("otpauth://totp/BnoBoard:%s?secret=%s&issuer=BnoBoard", user.getId(), user.getOtpSecretKey());
            return OtpSetupResponseDto.success(otpAuthUrl);
        } else {
            //  OTP Secret Key 생성
            String secretKey = otpService.generateSecretKey();
            String otpAuthUrl = String.format("otpauth://totp/BnoBoard:%s?secret=%s&issuer=BnoBoard", user.getId(), secretKey);

            //  DB에 OTP Secret Key 저장
            user.setOtpSecretKey(secretKey);
            userRepository.save(user);
            return OtpSetupResponseDto.success(otpAuthUrl);
        }
    }

    @PostMapping("/otp/activate")
    public ResponseEntity<?> activateOtp(@RequestHeader("Authorization") String authorizationHeader,
                                         @RequestBody Map<String, String> requestBody) {
        String token = authorizationHeader.replace("Bearer ", "");
        String userId = jwtTokenProvider.getUserPk(token);


        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null || user.getOtpSecretKey() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("OTP 설정이 되어 있지 않거나, 사용자가 존재하지 않습니다.");
        }

        String otpCodeStr = requestBody.get("otpCode");

        if (otpCodeStr == null || otpCodeStr.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("OTP 코드가 제공되지 않았습니다.");
        }

        try {
            int otpCode = Integer.parseInt(otpCodeStr);
            boolean isOtpValid = otpService.verifyOtp(user.getOtpSecretKey(), otpCode);

            if (!isOtpValid) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("OTP 인증 실패! 다시 입력해주세요.");
            }
            user.setOtpEnabled(true);
            userRepository.save(user);

            return ResponseEntity.ok("OTP 활성화 완료! 보안이 강화되었습니다.");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("OTP 입력값이 올바르지 않습니다.");
        }
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<? super GetUserLoginResponseDto> verifyOtp(@RequestBody OtpVerifyRequestDto requestDto, HttpSession session) {

        UserEntity user = userRepository.findByEmail(requestDto.getEmail()).orElse(null);
        if (user == null || user.getOtpSecretKey() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("OTP 설정이 되어 있지 않거나, 사용자가 존재하지 않습니다.");
        }

        boolean isOtpValid = otpService.verifyOtp(user.getOtpSecretKey(), Integer.parseInt(requestDto.getOtpCode()));

        if (!isOtpValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("OTP 인증 실패! 다시 입력해주세요.");
        }

        ResponseEntity<? super GetUserLoginResponseDto> response = userService.otpLoginPage(requestDto);
        session.setAttribute("loginDto", response);
        return response;
    }
}

