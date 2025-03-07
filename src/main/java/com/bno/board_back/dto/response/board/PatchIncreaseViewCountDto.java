package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Schema(description = "조회수 증가 응답 DTO")
@Getter
public class PatchIncreaseViewCountDto extends ResponseDto {

    private PatchIncreaseViewCountDto() { super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS); }

    @Schema(description = "성공", example = "true")
    public static ResponseEntity<PatchIncreaseViewCountDto> success() {
        PatchIncreaseViewCountDto result = new PatchIncreaseViewCountDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
