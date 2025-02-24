package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Schema(description = "게시글 작성 응답 DTO")
@Getter
public class PostWriteBoardResponseDto extends ResponseDto {

    private PostWriteBoardResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    @Schema(description = "작성 성공", example = "true")
    public static ResponseEntity<PostWriteBoardResponseDto> success() {
        PostWriteBoardResponseDto result = new PostWriteBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
