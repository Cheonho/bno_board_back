package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "boards")
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;
    private String title;
    private String writerEmail;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount = 0;
    private Boolean status = true;


    public void increaseViewCount() {
        this.viewCount++;
    }





}
