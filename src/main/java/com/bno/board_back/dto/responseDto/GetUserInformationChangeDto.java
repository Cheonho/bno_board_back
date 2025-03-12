package com.bno.board_back.dto.responseDto;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.dto.userDto.UserInformationChangeDto;
import com.bno.board_back.entity.UserEntity;
import com.bno.board_back.service.Mapper.UserInformationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetUserInformationChangeDto extends ResponseDto {

    private final UserInformationChangeDto userInformationChangeDto ;

    public GetUserInformationChangeDto(UserEntity userEntity) {
        super(ResponseCode.CHANGE_SUCCESS, ResponseMessage.CHANGE_SUCCESS);
        this.userInformationChangeDto = UserInformationMapper.INSTANCE.toDTO(userEntity);
    }

    public static ResponseEntity<ResponseDto> success(UserEntity userEntity) {
        GetUserInformationChangeDto result = new GetUserInformationChangeDto(userEntity) ;
        return ResponseEntity.status(HttpStatus.OK).body(result) ;
    }

}
