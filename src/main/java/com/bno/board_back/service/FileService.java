package com.bno.board_back.service;

import com.bno.board_back.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFileExtensionCheck(MultipartFile file);
    String dbSaveFile(MultipartFile file, String boardNum, String filePath, String fileUrl);

    String fileUpload(MultipartFile file, String boardNum);
    List<FileEntity> fileList(String boardNum);
    String deleteFile(String boardNum, List<MultipartFile> files);
}
