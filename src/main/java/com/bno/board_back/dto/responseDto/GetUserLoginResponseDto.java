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
    private final String token ;

    private GetUserLoginResponseDto(UserEntity userEntity, String token, boolean otpEnabled) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS) ;
        this.loginResponseDto = UserLoginMapper.INSTANCE.toDTO(userEntity) ;
        this.token = token;
    }

    public static ResponseEntity<GetUserLoginResponseDto> success(UserEntity userEntity, String token) {
        GetUserLoginResponseDto result = new GetUserLoginResponseDto(userEntity, token, userEntity.isOtpEnabled()) ;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

