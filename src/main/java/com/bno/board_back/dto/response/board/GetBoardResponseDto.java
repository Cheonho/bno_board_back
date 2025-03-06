package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private Long boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private Boolean status = true;

    public GetBoardResponseDto(BoardEntity board) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardNum = board.getBoardNum();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writerEmail = board.getWriterEmail();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
        this.viewCount = board.getViewCount();
        this.status = board.getStatus();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardEntity board) {
        GetBoardResponseDto result = new GetBoardResponseDto(board);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
