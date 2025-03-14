package com.bno.board_back.service.implement;

import com.bno.board_back.dto.responseDto.*;
import com.bno.board_back.dto.userDto.*;
import com.bno.board_back.entity.RefreshEntity;
import com.bno.board_back.entity.UserEntity;
import com.bno.board_back.provider.jwt.JwtTokenProvider;
import com.bno.board_back.provider.jwt.RefreshTokenProvider;
import com.bno.board_back.repository.RefreshRepository;
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

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenProvider refreshTokenProvider ;
    private final RefreshRepository refreshRepository;

    @Override
    public ResponseEntity<? super GetUserLoginResponseDto> loginPage(LoginRequestDto loginRequestDto) {

        // 1. 이메일로 사용자 조회
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(loginRequestDto.getEmail());
        if (optionalUserEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.loginFailed());
        }

        UserEntity userEntity = optionalUserEntity.get();

        // 2. 비밀번호 검증
        boolean passwordMatches = bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), userEntity.getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ResponseDto.loginFailed());
        }

        // 3. JWT 토큰 생성
        String accessToken = jwtTokenProvider.createToken(userEntity.getId(), userEntity.getUsername());
        String refreshToken = refreshTokenProvider.createRefreshToken(userEntity.getId(), userEntity.getEmail());
        // 4. RefreshToken 유무
        Optional<RefreshEntity>
                refreshTokenEntity = refreshRepository.findByEmail(loginRequestDto.getEmail()) ;

        RefreshEntity refreshEntity = refreshTokenEntity.get() ;
        System.out.println("❤ refreshEntity : " + refreshEntity);

        if (refreshEntity.getRefresh() != null && !refreshEntity.getRefresh().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDto.success());
        }

        refreshEntity = RefreshEntity.builder()
                .id(refreshEntity.getId())
                .email(refreshEntity.getEmail())
                .refresh(refreshToken)
                .expirationTime(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)
                .build() ;

        refreshRepository.save(refreshEntity) ;

        // 4. 로그인 성공 응답 반환
        return GetUserLoginResponseDto.success(userEntity, accessToken, refreshToken);
    }

    // 회원가입
    @Override
    public ResponseEntity<? super GetUserJoinResponseDto> joinPage(JoinRequestDto joinRequestDto, BindingResult bindingResult) {

        // 1. 이메일 또는 닉네임이 이미 존재하는지 확인
        Optional<UserEntity> existUserByEmail = userRepository.findByEmail(joinRequestDto.getEmail());
        Optional<UserEntity> existUserByNickname = userRepository.findByUserNickname(joinRequestDto.getUserNickname());

        if (existUserByEmail.isPresent() || existUserByNickname.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateUser());
        }

        // 2. 유효성 검사 결과가 있을 때
        if (bindingResult.hasErrors()) {
            // 오류 메시지를 수집
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.append(error.getDefaultMessage()).append(" ");
            }

            // 오류 메시지와 함께 BAD_REQUEST 상태 반환
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        // 3. 새로운 UserEntity 생성
        UserEntity userEntity = UserEntity.builder()
                .id(TsidUtilUseSystem.getTsid())
                .email(joinRequestDto.getEmail())
                .userNickname(joinRequestDto.getUserNickname())
                .password(bCryptPasswordEncoder.encode(joinRequestDto.getPassword()))
                .address(joinRequestDto.getAddress())
                .role(Collections.singletonList("USER").toString())
                .build();

        RefreshEntity refreshEntity = RefreshEntity.builder()
                .id(TsidUtilUseSystem.getTsid())
                .email(joinRequestDto.getEmail())
                .build() ;

        // 4. 저장
        userRepository.save(userEntity);
        refreshRepository.save(refreshEntity);

        return GetUserJoinResponseDto.success(userEntity, refreshEntity);
    } ;


    // 이메일 중복 확인
    @Override
    public ResponseEntity<? super GetUserCheckEmailDto> checkEmail(String email) {
        // 해당 이메일이 이미 사용 중인 경우 true를 반환
        boolean isAvailable = userRepository.existsByEmail(email);

        if (isAvailable) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateEmail());
        }
        return GetUserCheckEmailDto.usedemail();
    }

    // 닉네임 중복 확인
    @Override
    public ResponseEntity<? super GetUserCheckNicknameDto> checkNickname(String userNickname) {
        boolean isAvailable = userRepository.existsByUserNickname(userNickname);
        if (isAvailable) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateNickname());
        }
        return GetUserCheckEmailDto.usednickname();
    }

    // 닉네임 바꾸기
    @Override
    public ResponseEntity<? super GetUserInformationChangeDto> changeNickname(UserInformationChangeDto userInformationChangeDto) {

        // 중복된 닉네임인지 확인
        boolean existsUserNickname = userRepository.existsByUserNickname(userInformationChangeDto.getUserNickname());

        if (userInformationChangeDto.getUserNickname().isEmpty() || existsUserNickname) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateNickname());
        }

        Optional<UserEntity> originId = userRepository.findById(userInformationChangeDto.getId());

        if (originId.isPresent()) {
            UserEntity userEntity = originId.get();
            userEntity.setUserNickname(userInformationChangeDto.getUserNickname());
            userRepository.save(userEntity);

            return GetUserInformationChangeDto.changesuccess();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.notFoundUser());
        }
    }

    // 비밀번호 바꾸기
    @Override
    public ResponseEntity<? super GetUserInformationChangeDto> changePassword(UserInformationChangeDto userInformationChangeDto, BindingResult bindingResult) {

        Optional<UserEntity> originId = userRepository.findById(userInformationChangeDto.getId());
        UserEntity userEntity = originId.get();

        // 현재 비밀번호가 존재하는지
        boolean nowPwCheck = bCryptPasswordEncoder.matches(userInformationChangeDto.getNowpassword(), userEntity.getPassword());
        if (!nowPwCheck) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.notFoundPassword());
        }

        // 새 비밀번호가 서로 같지 않을 시
        if(!userInformationChangeDto.getPassword().equals(userInformationChangeDto.getCheckpassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.invalidpassword()) ;
        }

        // 2. 유효성 검사 결과가 있을 때
        if (bindingResult.hasErrors()) {
            // 오류 메시지를 수집
            StringBuilder errorMessages = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.append(error.getDefaultMessage()).append(" ");
            }
            // 오류 메시지와 함께 BAD_REQUEST 상태 반환

            return ResponseEntity.badRequest().body(errorMessages.toString());
        }
        userEntity.setPassword((bCryptPasswordEncoder.encode(userInformationChangeDto.getPassword())));
        userRepository.save(userEntity);

        return GetUserInformationChangeDto.changesuccess();
    }

    // 주소 바꾸기
    @Override
    public ResponseEntity<? super GetUserInformationChangeDto> changeAddress(UserInformationChangeDto userInformationChangeDto) {

        Optional<UserEntity> originId = userRepository.findById(userInformationChangeDto.getId());
        UserEntity userEntity = originId.get();
        if (userEntity.getAddress().equals(userInformationChangeDto.getAddress()) || userInformationChangeDto.getAddress().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDto.duplicateaddress());
        }
        userEntity.setAddress(userInformationChangeDto.getAddress());
        userRepository.save(userEntity);
        return GetUserInformationChangeDto.changesuccess();
    }

    // api 토큰
    @Override
    public ResponseEntity<? super GetUserApiTokenDto> apitokendata(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");

        // 2. 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("유효하지 않은 토큰입니다.");
        }

        // 3. 토큰에서 사용자 정보 추출
        String id = jwtTokenProvider.getUserPk(token);

        // 4. 사용자 정보 조회
        Optional<UserEntity> user = userRepository.findById(id); // 아이디로 사용자 조회
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDto.notFoundUser());
        }

        // 5. 사용자 데이터 반환 (GetUserApiTokenDto 형식으로 반환)
        UserEntity userEntity = user.get();

        ApitokendataDto apitokendataDto = ApitokendataDto.builder()
                .id((userEntity.getId()))
                .userNickname(userEntity.getUserNickname())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .address(userEntity.getAddress())
                .build() ;

        return GetUserApiTokenDto.apiSuccess(apitokendataDto);
    }
}
