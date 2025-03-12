package com.bno.board_back.common;

public interface ResponseCode {

    // Http status = 200
    public static final String SUCCESS = "SU"; // 성공
    String USED_EMAIL = "UES" ; // 이메일 사용 가능
    String USED_NICKNAME = "UNS" ; // 닉네임 사용 가능
    String JOIN_SUCCESS = "JS" ; // 회원가입 성공
    String LOGIN_SUCCESS = "LS" ; // 로그인 성공
    String CHANGE_SUCCESS = "CS" ; // 변경 성공
    String PASSWORD_SUCCESS = "PS" ; // 비밀번호 일치

    // Http status 400
    String INVALID_PASSWORD = "IP";  // 잘못된 입력
    String DUPLICATE_NICKNAME = "DN"; // 닉네임 중복
    String DUPLICATE_EMAIL = "DE"; // 이메일 중복
    String PASSWORD_TOO_WEAK = "PW"; // 비밀번호가 너무 약함
    String DUPLICATE_USER = "DU" ; // 중복된 사용자
    String DUPLICATE_ADDRESS = "DA" ;// 똑같은 주소지

    String VALIDATION_FAILED = "VF";
    String DUPLICATE_NICK = "DN";

    // Http status 404
    String USER_NOT_FOUND = "UNF"; // 사용자 찾을 수 없음
    String EMAIL_NOT_FOUND = "ENF"; // 이메일 찾을 수 없음
    String NICKNAME_NOT_FOUND = "NNF"; // 닉네임 찾을 수 없음
    String PASSWORD_NOT_FOUND = "PNF"; // 비밀번호 찾을 수 없음
    String ADDRESS_NOT_FOUND = "ANF"; // 주소 찾을 수 없음

    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    // Http status 401
    String SIGN_IN_FAILED = "SIF"; // 로그인 실패
    String AUTHORIZATION_FAILED = "AF"; // 권한 실패

    String SIGN_IN_FAIL = "LF";

    // Http status 403
    String NO_PERMISSION = "NP"; // 권한 없음

    // Http status 500
    String DATABASE_ERROR = "DBE"; // 데이터베이스 오류
}
