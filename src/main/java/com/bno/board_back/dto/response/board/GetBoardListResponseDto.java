package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.dto.object.PageDto;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.mapper.BoardListViewMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@ToString
public class GetBoardListResponseDto extends PageDto {

    private final List<BoardListView> boardList;

    private GetBoardListResponseDto(Page<BoardListViewEntity> page) {
        super(page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());
        this.boardList = BoardListViewMapper.INSTANCE.toDtoList(page.getContent());
    }

    public static ResponseEntity<GetBoardListResponseDto> sucess(Page<BoardListViewEntity> page) {
        GetBoardListResponseDto result = new GetBoardListResponseDto(page);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
