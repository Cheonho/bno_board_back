package com.bno.board_back.utils;

import com.bno.board_back.dto.object.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class PaginationUtil {

    public static final String X_TOTAL_ELEMENTS = "X-Total-Elements";
    public static final String X_TOTAL_PAGE = "X-Total-Page";
    public static final String X_PAGE_NUMBER = "X-Page-Number";
    public static final String X_PAGE_SIZE = "X-Page-Size";
    public static final String X_CURRENT_SECTION = "X-Current-Section";
    public static final String X_FIRST_PAGE_NUMBER = "X-First-Page-Number";
    public static final String X_LAST_PAGE_NUMBER = "X-Last-Page-Number";


    public static HttpHeaders generatePageHeaders(PageDto page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_ELEMENTS, String.valueOf(page.getTotalElements()));
        headers.add(X_TOTAL_PAGE, String.valueOf(page.getTotalPage()));
        headers.add(X_PAGE_NUMBER, String.valueOf(page.getPageNumber()));
        headers.add(X_PAGE_SIZE, String.valueOf(page.getPageSize()));
        headers.add(X_CURRENT_SECTION, String.valueOf(page.getCurrentSection()));
        headers.add(X_FIRST_PAGE_NUMBER, String.valueOf(page.getFirstPageNumber()));
        headers.add(X_LAST_PAGE_NUMBER, String.valueOf(page.getLastPageNumber()));

        return headers;
    }
}
