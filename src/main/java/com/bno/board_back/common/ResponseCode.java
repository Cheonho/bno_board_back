package com.bno.board_back.common;


public interface ResponseCode {

    // Http status = 200
    public static final String SUCCESS = "SU"; // public static final 지워도됨

    // Http status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_NICK = "DN";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    // Http status 401
    String SIGN_IN_FAIL = "LF";
    String AUTHORIZATION_FAIL = "AF";

    // Http status 403
    String NO_PERMISSION = "NP";

    // Http status 500
    String DATABASE_ERROR = "DBE";
}
