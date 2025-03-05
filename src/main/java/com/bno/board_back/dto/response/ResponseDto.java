package com.bno.board_back.dto.response;

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

    public static ResponseEntity<ResponseDto> databseError() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> authError() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.AUTHORIZATION_FAILED, ResponseMessage.AUTHORIZATION_FAILED);
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> signFailed() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> notFoundBoard() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> notFoundUser() {
        ResponseDto responseDto = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.USER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
}
