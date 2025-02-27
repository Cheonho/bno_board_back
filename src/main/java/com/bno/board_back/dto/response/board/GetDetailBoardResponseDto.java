package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.mapper.BoardListViewMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@ToString
public class GetDetailBoardResponseDto extends ResponseDto {

    private final BoardListView boardListView;

    private GetDetailBoardResponseDto(BoardListViewEntity boardListView) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardListView = BoardListViewMapper.INSTANCE.toDTO(boardListView);
    }

    public static ResponseEntity<GetDetailBoardResponseDto> success(BoardListViewEntity boardListView) {
        GetDetailBoardResponseDto result = new GetDetailBoardResponseDto(boardListView);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
