package com.bno.board_back.common;

public interface ResponseMessage {

    // Http status = 200
    public static final String SUCCESS = "성공"; // 성공
    String USED_EMAIL = "사용 가능한 이메일입니다." ; // 이메일 사용 가능
    String USED_NICKNAME = "사용 가능한 닉네임입니다." ; // 닉네임 사용 가능
    String JOIN_SUCCESS = "회원가입 성공" ; // 회원가입 성공
    String LOGIN_SUCCESS = "로그인 성공" ; // 로그인 성공

    // Http status 400
    String INVALID_INPUT = "잘못된 입력";  // 잘못된 입력
    String DUPLICATE_NICKNAME = "닉네임이 존재합니다."; // 닉네임 중복
    String DUPLICATE_EMAIL = "이메일이 존재합니다."; // 잘못된 이메일 형식
    String PASSWORD_TOO_WEAK = "비밀번호가 너무 약함"; // 비밀번호가 너무 약함
    String DUPLICATE_USER = "중복 확인을 해 주세요." ;

    // Http status 404
    String USER_NOT_FOUND = "사용자를 찾을 수 없습니다."; // 사용자 찾을 수 없음
    String EMAIL_NOT_FOUND = "이메일을 찾을 수 없습니다."; // 이메일 찾을 수 없음
    String NICKNAME_NOT_FOUND = "닉네임을 찾을 수 없습니다."; // 닉네임 찾을 수 없음
    String PASSWORD_NOT_FOUND = "비밀번호를 찾을 수 없습니다."; // 비밀번호 찾을 수 없음
    String ADDRESS_NOT_FOUND = "주소를 찾을 수 없습니다."; // 주소 찾을 수 없음

    // Http status 401
    String SIGN_IN_FAILED = "로그인 실패"; // 로그인 실패
    String AUTHORIZATION_FAILED = "권한 인증 실패"; // 권한 인증 실패

    // Http status 403
    String NO_PERMISSION = "권한이 없습니다."; // 권한 없음

    // Http status 500
    String DATABASE_ERROR = "데이터베이스 오류"; // 데이터베이스 오류
}
