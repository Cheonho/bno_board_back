package com.bno.board_back.dto.responseDto;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.CheckEmailNicknameDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetUserCheckNicknameDto extends ResponseDto {

    private final CheckEmailNicknameDto checkEmailNicknameDto;

    public GetUserCheckNicknameDto(UserEntity userEntity) {
        super(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        this.checkEmailNicknameDto = new CheckEmailNicknameDto();
    }

    ;

    public static ResponseEntity<GetUserCheckNicknameDto> usednickname(UserEntity userEntity) {
        GetUserCheckNicknameDto result = new GetUserCheckNicknameDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
