package com.bno.board_back.service;

import com.bno.board_back.dto.object.FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    boolean fileUpload(MultipartFile fileDto, String boardNum);
    void uploadFileSizeCheck(MultipartFile file);
}
