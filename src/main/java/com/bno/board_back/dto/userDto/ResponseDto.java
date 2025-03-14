package com.bno.board_back.dto.userDto;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    // 성공
    public static ResponseEntity<ResponseDto> success() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 회원 정보 변경 성공
    public static ResponseEntity<ResponseDto> changesuccess() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.CHANGE_SUCCESS, ResponseMessage.CHANGE_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    public static ResponseEntity<ResponseDto> usedemail() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.USED_EMAIL, ResponseMessage.USED_EMAIL);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> usednickname() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.USED_NICKNAME, ResponseMessage.USED_NICKNAME);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> joinsuccess() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.JOIN_SUCCESS, ResponseMessage.JOIN_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> loginsuccess() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.LOGIN_SUCCESS, ResponseMessage.LOGIN_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> passwordchecksuccess() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.PASSWORD_SUCCESS, ResponseMessage.PASSWORD_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 데이터베이스 오류
    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    // 권한 인증 실패
    public static ResponseEntity<ResponseDto> authError() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.AUTHORIZATION_FAILED, ResponseMessage.AUTHORIZATION_FAILED);
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(responseDto);
    }

    // 로그인 실패
    public static ResponseEntity<ResponseDto> loginFailed() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.SIGN_IN_FAILED, ResponseMessage.SIGN_IN_FAILED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    // 사용자 찾을 수 없음
    public static ResponseEntity<ResponseDto> notFoundUser() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.USER_NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    // 닉네임 찾을 수 없음
    public static ResponseEntity<ResponseDto> notFoundNickname() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.NICKNAME_NOT_FOUND, ResponseMessage.NICKNAME_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    // 비밀번호 찾을 수 없음
    public static ResponseEntity<ResponseDto> notFoundPassword() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.PASSWORD_NOT_FOUND, ResponseMessage.PASSWORD_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    // 주소 찾을 수 없음
    public static ResponseEntity<ResponseDto> notFoundAddress() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.ADDRESS_NOT_FOUND, ResponseMessage.ADDRESS_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    // 닉네임 중복
    public static ResponseEntity<ResponseDto> duplicateNickname() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    // 잘못된 입력
    public static ResponseEntity<ResponseDto> invalidpassword() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.INVALID_PASSWORD, ResponseMessage.INVALID_PASSWORD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    // 이메일 존재합니다.
    public static ResponseEntity<ResponseDto> duplicateEmail() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    // 비밀번호가 너무 약함
    public static ResponseEntity<ResponseDto> passwordTooWeak() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.PASSWORD_TOO_WEAK, ResponseMessage.PASSWORD_TOO_WEAK);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    // 중복된 사용자
    public static ResponseEntity<ResponseDto> duplicateUser() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.DUPLICATE_USER, ResponseMessage.DUPLICATE_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    // 중복된 사용자
    public static ResponseEntity<ResponseDto> duplicateaddress() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.DUPLICATE_ADDRESS, ResponseMessage.DUPLICATE_ADDRESS);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
