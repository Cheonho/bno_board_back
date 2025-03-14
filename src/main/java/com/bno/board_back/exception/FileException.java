package com.bno.board_back.exception;


import lombok.Getter;

@Getter
public class FileException extends RuntimeException {
    public FileException(String message, Object errorData) {
        super(message);
        this.errorData = errorData;
    }

    private final Object errorData;
}
