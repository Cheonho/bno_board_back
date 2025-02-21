package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.BoardViewList;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {

    private List<BoardViewList> boardSearchList;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;

    private GetSearchBoardListResponseDto(Page<BoardListViewEntity> page) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardSearchList = BoardViewList.getList(page.getContent());
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    public static ResponseEntity<GetSearchBoardListResponseDto> success(Page<BoardListViewEntity> boardListViewEntities) {
        GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
