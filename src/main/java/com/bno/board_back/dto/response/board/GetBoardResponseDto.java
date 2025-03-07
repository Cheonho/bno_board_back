package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private String boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private String writerNickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;

    public GetBoardResponseDto(BoardListViewEntity board) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardNum = String.valueOf(board.getBoardNum());
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writerEmail = board.getWriterEmail();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
        this.viewCount = board.getViewCount();
        this.writerNickname = board.getWriterNickname();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardListViewEntity board) {
        GetBoardResponseDto result = new GetBoardResponseDto(board);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
