package com.bno.board_back.dto.responseDto;


import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.userDto.JoinResponseDto;
import com.bno.board_back.dto.userDto.ResponseDto;
import com.bno.board_back.entity.UserEntity;
import com.bno.board_back.service.Mapper.UserJoinMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public class GetUserJoinResponseDto extends ResponseDto {

    private final JoinResponseDto joinResponseDto;

    private GetUserJoinResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.joinResponseDto = UserJoinMapper.INSTANCE.toDTO(userEntity);
    }

    public static ResponseEntity<ResponseDto> success(UserEntity userEntity) {
        GetUserJoinResponseDto result = new GetUserJoinResponseDto(userEntity) ;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
