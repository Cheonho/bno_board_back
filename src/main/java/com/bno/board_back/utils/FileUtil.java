package com.bno.board_back.utils;

import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    public static String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("파일명이 비어 있습니다.");
        }

        int lastIndex = originalFilename.lastIndexOf(".");
        if (lastIndex == -1) {
            throw new IllegalArgumentException("확장자가 없는 파일입니다: " + originalFilename);
        }

        return originalFilename.substring(lastIndex + 1); // 확장자 반환
    }

    public static List<FileDto> toFileDtoFromMultipartFile(List<MultipartFile> files, String boardNum) {
        if (files == null || files.isEmpty()) return null;
        if (boardNum.isEmpty()) return null;

        List<FileDto> fileDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            String path = UUID.randomUUID().toString().split("-")[0];
            String fullPath = boardNum + "/" + path + "/" + file.getOriginalFilename();

            FileDto fileDto = FileDto.builder()
//                    .id(file.getId() ? file.getId() : TsidUtilUseSystem.getTsid())
                    .fileName(file.getOriginalFilename())
                    .boardNum(boardNum)
                    .filePath(fullPath)
//                    .minioDataUrl(file.)
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .fileExtension(getFileExtension(file))
                    .build();

            fileDtos.add(fileDto);
        }
        return fileDtos;
    }
}
