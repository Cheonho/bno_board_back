package com.bno.board_back.service.implement;

import com.bno.board_back.config.MinioConfig;
import com.bno.board_back.dto.object.FileDto;
import com.bno.board_back.entity.FileEntity;
import com.bno.board_back.exception.CustomException;
import com.bno.board_back.exception.FileException;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.repository.FileRepository;
import com.bno.board_back.service.FileService;
import com.bno.board_back.utils.FileUtil;
import com.bno.board_back.utils.TsidUtilUseSystem;
import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.bno.board_back.common.ResponseMessage.*;

@Service
public class FileServiceImplement implements FileService {

    private final MinioClient minioClient;
    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;

    private final Logger logger = LoggerFactory.getLogger(FileServiceImplement.class);

    @Value("#{'${file.allowed.file_extension}'.split(',')}")
    ArrayList<String> allowedFileExtensions;

    @Value("${minio.bucket.name}")
    private String bucketName;

    public FileServiceImplement(MinioClient minioClient, FileRepository fileRepository, BoardRepository boardRepository) {
        this.minioClient = minioClient;
        this.fileRepository = fileRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void uploadFileExtensionCheck(MultipartFile file) {
        String extension = FileUtil.getFileExtension(file).toLowerCase();
        if (!allowedFileExtensions.contains(extension)) {
            throw new FileException(FILE_EXTENSION, FILE_EXTENSION);
        }
    }

    @Override
    public String dbSaveFile(MultipartFile file, String boardNum, String filePath, String fileUrl) {
        if (file.isEmpty() || file.getSize() <= 0) {return NON_FILE_EXISTED;}
        if (!boardRepository.existsByBoardNum(boardNum)) {return NOT_EXISTED_BOARD;}
        try {
            FileEntity fileEntity = FileEntity.builder()
                    .id(TsidUtilUseSystem.getTsid())
                    .fileName(file.getOriginalFilename())
                    .boardNum(boardNum)
                    .filePath(filePath)
                    .minioDataUrl(fileUrl)
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .fileExtension(FileUtil.getFileExtension(file).toLowerCase())
                    .updateAt(null)
                    .createAt(LocalDateTime.now())
                    .status(true)
                    .build();

            fileRepository.save(fileEntity);
            return SUCCESS;
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SUCCESS;
        }
    }

    @Transactional
    @Override
    public String fileUpload(MultipartFile file, String boardNum) {
        System.out.println("MinioConfig.getBucketName() : ---------- " + bucketName);
        String originFileName = file.getOriginalFilename();
        String path = UUID.randomUUID().toString().split("-")[0];
        String fullPath = boardNum + "/" + path + "/" + originFileName;

        this.uploadFileExtensionCheck(file);

        try {
            // bucket 존재여부 확인
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if(!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            // 파일 업로드
            minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName)
                    .object(fullPath) // 파일 경로
                    .stream(file.getInputStream(), file.getSize(), -1) // minio 서버로 데이터 전송
                    .contentType(file.getContentType())
                    .build()
            );

            // 다운로드 URL
            String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(fullPath)
                    .expiry(10, TimeUnit.MINUTES) // 다운로드 시간 제한
                    .build());

            String fileMessage = dbSaveFile(file, boardNum, fullPath, url);

            if (fileMessage.equals(SUCCESS)) {
                return SUCCESS;
            } else {
                minioClient.removeObject(
                    RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fullPath)
                        .build());
                throw new FileException(fileMessage, fileMessage);
            }

            // uploadDTO 객체 리턴
//            return new FileDto().builder()
//                    .fileName(originFileName)
//                    .roomId(roomId)
//                    .filePath(fullPath)
//                    .minioDataUrl(url)
//                    .contentType(file.getContentType())
//                    .status(FileDto.Status.UPLOADED)
//                    .build();
        } catch (Exception e) {
            e.printStackTrace();

//            return new FileDto().builder()
//                    .status(FileDto.Status.FAIL)
//                    .build();
            throw new FileException(DATABASE_ERROR, DATABASE_ERROR);
        }
    }

    @Transactional
    @Override
    public List<FileEntity> fileList(String boardNum) {
        List<FileEntity> fileList;
        if (boardRepository.existsByBoardNum(boardNum)) {
            fileList = fileRepository.findAllByBoardNumAndStatusTrue(boardNum);
        } else {
            throw new CustomException(NOT_EXISTED_BOARD, NOT_EXISTED_BOARD, "NotFound", HttpStatus.NOT_FOUND);
        }
        return fileList;
    }
}
