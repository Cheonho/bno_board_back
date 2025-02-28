package com.bno.board_back.dto.response.path;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.Path;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.PathEntity;
import com.bno.board_back.mapper.PathMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@ToString
public class GetPathListResponseDto extends ResponseDto {

    private final List<Path> pathList;

    private GetPathListResponseDto(List<PathEntity> pathListEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.pathList = PathMapper.INSTANCE.toDtoList(pathListEntity);
    }

    public static ResponseEntity<GetPathListResponseDto> success(List<PathEntity> pathListEntity) {
        GetPathListResponseDto result = new GetPathListResponseDto(pathListEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
