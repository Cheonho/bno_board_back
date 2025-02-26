package com.bno.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardListView {

    private String boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private String writerNickname;
}
