package com.bno.board_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false, of = "id")
@Entity(name="files")
@Table(name="files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity extends AbstractIdEntity {

    @Id
    private String id;
    private String fileName;  // 파일 원본 이름
    private String boardNum; // 파일이 올라간 board
    private String filePath; // 파일 fullpath
    private String minioDataUrl; // 파일 링크
    private String contentType; // 타입
    private Long size; // 파일 크기
    private String fileExtension; // 파일 확장자
    private LocalDateTime createAt;
    private Boolean status;
}
