package com.bno.board_back.service.implement;

import com.bno.board_back.config.MinioConfig;
import com.bno.board_back.dto.object.FileDto;
import com.bno.board_back.exception.FileException;
import com.bno.board_back.service.FileService;
import com.bno.board_back.utils.FileUtil;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.bno.board_back.common.ResponseMessage.FILE_EXTENSION;

@Service
public class FileServiceImplement implements FileService {

    private final MinioClient minioClient;

    @Value("#{'${file.allowed.file_extension}'.split(',')}")
    ArrayList<String> allowedFileExtensions;

    public FileServiceImplement(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void uploadFileSizeCheck(MultipartFile file) {
        String extension = FileUtil.getFileExtension(file).toLowerCase();
        if (!allowedFileExtensions.contains(extension)) {
            throw new FileException(FILE_EXTENSION);
        }
    }

    @Transactional
    @Override
    public boolean fileUpload(MultipartFile file, String boardNum) {
        System.out.println("MinioConfig.getBucketName() : ---------- " + MinioConfig.getBucketName());
        String originFileName = file.getOriginalFilename();
        String path = UUID.randomUUID().toString().split("-")[0];
        String fullPath = boardNum + "/" + path + "/" + originFileName;

        this.uploadFileSizeCheck(file);

        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(MinioConfig.getBucketName()).build());
            if(!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinioConfig.getBucketName()).build());
            }
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(MinioConfig.getBucketName())
                            .object(fullPath)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(MinioConfig.getBucketName())
                            .object(fullPath)
                            .expiry(10, TimeUnit.MINUTES) // 다운로드 시간 제한
                            .build());

            // uploadDTO 객체 리턴
//            return new FileDto().builder()
//                    .fileName(originFileName)
//                    .roomId(roomId)
//                    .filePath(fullPath)
//                    .minioDataUrl(url)
//                    .contentType(file.getContentType())
//                    .status(FileDto.Status.UPLOADED)
//                    .build();
            return true;

        } catch (Exception e) {
            e.printStackTrace();

//            return new FileDto().builder()
//                    .status(FileDto.Status.FAIL)
//                    .build();
            return false;
        }
    }
}
