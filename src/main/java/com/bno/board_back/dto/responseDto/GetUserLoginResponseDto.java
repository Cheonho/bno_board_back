package com.bno.board_back.dto.responseDto;


import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.LoginResponseDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import com.bno.board_back.service.Mapper.UserLoginMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public class GetUserLoginResponseDto extends ResponseDto {

    private final LoginResponseDto loginResponseDto ;

    private GetUserLoginResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS) ;
        this.loginResponseDto = UserLoginMapper.INSTANCE.toDTO(userEntity) ;
    }

    public static ResponseEntity<GetUserLoginResponseDto> success(UserEntity userEntity) {
        GetUserLoginResponseDto result = new GetUserLoginResponseDto(userEntity) ;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

