package com.bno.board_back.dto.object;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class PageDto extends ResponseDto {

    private final int pageNumSize = 5;
    private final int totalPage;
    private final long totalElements;
    private final int pageNumber;
    private final int pageSize;
    private final int currentSection;
    private final int firstPageNumber;
    private final int lastPageNumber;

    public PageDto(int totalPage, long totalElements, int pageNumber, int pageSize) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.totalPage = totalPage;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;

        this.currentSection = (pageNumber / pageNumSize) + 1;
        this.firstPageNumber = pageNumSize * (currentSection - 1) + 1;
        this.lastPageNumber = Math.min(currentSection * pageNumSize, totalPage);
    }
}
