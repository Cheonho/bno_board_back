package com.bno.board_back.service;

import com.bno.board_back.dto.object.FileDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String fileUpload(MultipartFile fileDto, String boardNum);
    void uploadFileExtensionCheck(MultipartFile file);
    String dbSaveFile(MultipartFile file, String boardNum, String filePath, String fileUrl);
}
