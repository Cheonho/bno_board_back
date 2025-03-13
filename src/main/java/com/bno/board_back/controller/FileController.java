package com.bno.board_back.controller;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.file.GetRefreshFileUrlDto;
import com.bno.board_back.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam("boardNum") String boardNum
    ) {
        String test = fileService.fileUpload(files, boardNum);
        return null;
    }

    @GetMapping(value = "refresh-url/{fileId}")
    public ResponseEntity<? super GetRefreshFileUrlDto> refreshFiledUrl(
            @PathVariable String fileId
    ) {
        String refreshUrl = fileService.refreshFileUrl(fileId);
        return GetRefreshFileUrlDto.success(refreshUrl);
    }
}
