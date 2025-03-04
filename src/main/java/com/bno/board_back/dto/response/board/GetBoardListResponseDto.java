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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@ToString
public class GetBoardListResponseDto extends ResponseDto {

    private final List<BoardListView> boardList;
    private static PageDto pageDto = null;

    private GetBoardListResponseDto(Page<BoardListViewEntity> page) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardListViewMapper.INSTANCE.toDtoList(page.getContent());
    }

    public static ResponseEntity<GetBoardListResponseDto> sucess(Page<BoardListViewEntity> page) {
        GetBoardListResponseDto result = new GetBoardListResponseDto(page);
        pageDto = new PageDto(page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Elements", String.valueOf(pageDto.getTotalElements()));
        headers.add("X-Total-Page", String.valueOf(pageDto.getTotalPage()));
        headers.add("X-Page-Number", String.valueOf(pageDto.getPageNumber()));
        headers.add("X-Page-Size", String.valueOf(pageDto.getPageSize()));
        headers.add("X-Current-Section", String.valueOf(pageDto.getCurrentSection()));
        headers.add("X-First-Page-Number", String.valueOf(pageDto.getFirstPageNumber()));
        headers.add("X-Last-Page-Number", String.valueOf(pageDto.getLastPageNumber()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result);
    }
}
