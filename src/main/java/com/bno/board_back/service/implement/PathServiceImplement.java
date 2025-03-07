package com.bno.board_back.service.implement;

import com.bno.board_back.dto.response.path.GetPathListResponseDto;
import com.bno.board_back.entity.PathEntity;
import com.bno.board_back.repository.PathRepository;
import com.bno.board_back.service.PathService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PathServiceImplement implements PathService {

    private final Logger logger = LoggerFactory.getLogger(PathServiceImplement.class);
    private final PathRepository pathRepository;

    @Override
    public ResponseEntity<? super GetPathListResponseDto> getPathList() {

        List<PathEntity> pathListEntity;

        try{
            pathListEntity = pathRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

        return GetPathListResponseDto.success(pathListEntity);
    }
}
