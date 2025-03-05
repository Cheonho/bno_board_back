package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.dto.object.PageDto;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.mapper.BoardListViewMapper;
import com.bno.board_back.utils.PaginationUtil;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {

    private final List<BoardListView> boardList;

    private GetSearchBoardListResponseDto(Page<BoardListViewEntity> page) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardListViewMapper.INSTANCE.toDtoList(page.getContent());
    }

    public static ResponseEntity<GetSearchBoardListResponseDto> success(Page<BoardListViewEntity> boardListViewEntities) {
        GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(boardListViewEntities);
        PageDto pageDto = new PageDto(boardListViewEntities.getTotalPages(), boardListViewEntities.getTotalElements(), boardListViewEntities.getNumber(), boardListViewEntities.getSize());
        HttpHeaders headers = PaginationUtil.generatePageHeaders(pageDto);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }
}
