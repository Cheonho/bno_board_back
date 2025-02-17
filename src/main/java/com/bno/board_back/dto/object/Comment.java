package com.bno.board_back.dto.object;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private int commentNum;
    private String content;
    private int commentLevel;
    private int parentNum;
    private String writer;
    private int boardNum;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
