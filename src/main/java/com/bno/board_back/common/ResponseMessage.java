package com.bno.board_back.common;

public interface ResponseMessage {

    // Http status = 200
    public static final String SUCCESS = "success"; // public static final 지워도됨

    // Http status 400
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATE_NICK = "Duplicated nickname";
    String NOT_EXISTED_USER = "User does not exist.";
    String NOT_EXISTED_BOARD = "Board does not exist.";

    // Http status 401
    String SIGN_IN_FAIL = "Login fail";
    String AUTHORIZATION_FAIL = "Authorization Failed";

    // Http status 403
    String NO_PERMISSION = "Do not have permission";

    // Http status 500
    String DATABASE_ERROR = "Database error";
}
