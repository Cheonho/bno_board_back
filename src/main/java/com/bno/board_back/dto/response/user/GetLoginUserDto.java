package com.bno.board_back.dto.response.user;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.Users;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetLoginUserDto extends ResponseDto {

    Users user;

    private GetLoginUserDto(Users user) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.user = user;
    }

    public static ResponseEntity<GetLoginUserDto> success(UserEntity resUser) {
        Users user = new Users(resUser);
        GetLoginUserDto result = new GetLoginUserDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
