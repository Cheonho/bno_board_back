package com.bno.board_back.controller;

import com.bno.board_back.dto.response.path.GetPathListResponseDto;
import com.bno.board_back.service.PathService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path/")
@Tag(name="Path", description = "Path API")
public class PathController {

    private final PathService pathService;

    public PathController(PathService pathService) {this.pathService = pathService;}

    @Operation(summary = "전체 url 조회", description = "등록되 url을 조회 합니다")
    @GetMapping("path-list")
    public ResponseEntity<? super GetPathListResponseDto> getPathList() {
        ResponseEntity<? super GetPathListResponseDto> response = pathService.getPathList();
        return response;
    }
}
