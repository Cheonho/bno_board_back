package com.bno.board_back.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    public CustomException(String message, Object errorData, String errorName, HttpStatus status) {
        super(message);
        this.errorData = errorData;
        this.errorName = errorName;
        this.status = status;
    }

    private final Object errorData;
    private final String errorName;
    private final HttpStatus status;
}
