package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false, of = "boardNum")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Audited
@Entity(name="board")
@Table(name="boards")
public class BoardEntity extends AbstractBoardNumEntity {

    @Id
    private Long boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private Boolean status;
}
