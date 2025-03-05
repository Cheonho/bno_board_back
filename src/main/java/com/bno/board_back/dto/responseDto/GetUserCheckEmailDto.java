package com.bno.board_back.dto.responseDto;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.CheckEmailNicknameDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public class GetUserCheckEmailDto extends ResponseDto {

    private final CheckEmailNicknameDto checkEmailNicknameDto ;

    public GetUserCheckEmailDto(UserEntity userEntity) {
        super(ResponseCode.USED_EMAIL, ResponseMessage.USED_EMAIL) ;
        this.checkEmailNicknameDto = new CheckEmailNicknameDto() ;
    } ;

    public static ResponseEntity<GetUserCheckEmailDto> usedemail(UserEntity userEntity) {
        GetUserCheckEmailDto result = new GetUserCheckEmailDto(userEntity) ;
        return ResponseEntity.status(HttpStatus.OK).body(result) ;
    }
}
