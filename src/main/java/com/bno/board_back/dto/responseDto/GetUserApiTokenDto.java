package com.bno.board_back.dto.responseDto;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.ApitokendataDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public class GetUserApiTokenDto extends ResponseDto {

    private final ApitokendataDto apitokendataDto;

    public GetUserApiTokenDto(ApitokendataDto apitokendataDto) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.apitokendataDto = apitokendataDto; // 이렇게 해야 받은 데이터 저장
    }

    public static ResponseEntity<GetUserApiTokenDto> apiSuccess(ApitokendataDto apitokendataDto) {
        GetUserApiTokenDto result = new GetUserApiTokenDto(apitokendataDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}