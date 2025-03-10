package com.bno.board_back.dto.responseDto;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.ApitokendataDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public class GetUserApiTokenDto extends ResponseDto {

    private final ApitokendataDto apitokendataDto;

    public GetUserApiTokenDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.apitokendataDto = new ApitokendataDto();
    }

    public static ResponseEntity<GetUserApiTokenDto> usedemail(UserEntity userEntity) {
        GetUserApiTokenDto result = new GetUserApiTokenDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}