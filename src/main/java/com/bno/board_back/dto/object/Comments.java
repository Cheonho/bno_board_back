package com.bno.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    private int commentNum;
    private String content;
    private int commentLevel;
    private int parentNum;
    private String writer;
    private int boardNum;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
