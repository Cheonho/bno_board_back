package com.bno.board_back.dto.response.board;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.service.mapper.BoardListViewMapper;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetBoardListResponseDto extends ResponseDto {

    private final List<BoardListView> boardList;
    private final int totalPages;
    private final long totalElements;
    private final int pageNumber;
    private final int pageSize;

    private GetBoardListResponseDto(Page<BoardListViewEntity> page) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardListViewMapper.INSTANCE.toDtoList(page.getContent());
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }

    public static ResponseEntity<GetBoardListResponseDto> sucess(Page<BoardListViewEntity> page) {
        GetBoardListResponseDto result = new GetBoardListResponseDto(page);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
