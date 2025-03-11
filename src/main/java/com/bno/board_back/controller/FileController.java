package com.bno.board_back.controller;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file/")
@Tag(name = "File", description = "파일 관련 API")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> uploadFile(
            @RequestPart("file") MultipartFile file,
            @RequestParam("boardNum") String boardNum
    ) {
        String test = fileService.fileUpload(file, boardNum);
        return null;
    }
}
