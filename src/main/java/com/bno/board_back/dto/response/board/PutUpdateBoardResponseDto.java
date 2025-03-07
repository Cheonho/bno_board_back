package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Schema(description = "게시글 수정 응답 DTO")
@Getter
public class PutUpdateBoardResponseDto extends ResponseDto {

    private PutUpdateBoardResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    @Schema(description = "수정 성공", example = "true")
    public static ResponseEntity<PutUpdateBoardResponseDto> success() {
        PutUpdateBoardResponseDto result = new PutUpdateBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
