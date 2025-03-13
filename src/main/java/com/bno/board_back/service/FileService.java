package com.bno.board_back.service;

import com.bno.board_back.entity.FileEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFileExtensionCheck(MultipartFile file);
    void uploadFileSizeCheck(MultipartFile file);

    String dbSaveFile(MultipartFile file, String boardNum, String filePath, String fileUrl);

    String fileUpload(List<MultipartFile> files, String boardNum);
    List<FileEntity> fileList(String boardNum);
    String deleteFile(String boardNum, List<String> filesId);
    String refreshFileUrl(String fileId);
}
