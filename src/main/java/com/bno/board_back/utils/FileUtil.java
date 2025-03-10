package com.bno.board_back.utils;

import org.springframework.web.multipart.MultipartFile;

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
}
