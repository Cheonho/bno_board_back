package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.FileDto;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.entity.FileEntity;
import com.bno.board_back.mapper.FileMapper;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private final String boardNum;
    private final String title;
    private final String content;
    private final String writerEmail;
    private final String writerNickname;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private final int viewCount;
    private final List<FileDto> files;

    public GetBoardResponseDto(BoardListViewEntity board, List<FileEntity> files) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardNum = String.valueOf(board.getBoardNum());
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writerEmail = board.getWriterEmail();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
        this.viewCount = board.getViewCount();
        this.writerNickname = board.getWriterNickname();
        if (files != null && !files.isEmpty()) {
            this.files = FileMapper.INSTANCE.toDtoList(files);
        } else {
            this.files = new ArrayList<>();
        }
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardListViewEntity board, List<FileEntity> files) {
        GetBoardResponseDto result = new GetBoardResponseDto(board, files);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
