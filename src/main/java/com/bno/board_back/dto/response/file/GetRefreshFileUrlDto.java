package com.bno.board_back.dto.response.file;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetRefreshFileUrlDto extends ResponseDto {

    private final String refreshUrl;

    public GetRefreshFileUrlDto(String refreshUrl) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.refreshUrl = refreshUrl;
    }

    public static ResponseEntity<GetRefreshFileUrlDto> success(String refreshUrl) {
        GetRefreshFileUrlDto result = new GetRefreshFileUrlDto(refreshUrl);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
