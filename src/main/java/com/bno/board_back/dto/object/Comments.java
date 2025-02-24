package com.bno.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    private Long commentNum;
    private String content;
    private int parentNum;
    private String writerEmail;
    private int boardNum;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int status;
}
