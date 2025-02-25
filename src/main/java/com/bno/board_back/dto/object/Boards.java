package com.bno.board_back.dto.object;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Boards {

    @Schema(hidden = true)
    private Long boardNum;
    @NotBlank
    @Schema(description = "제목", example = "test")
    private String title;
    @NotBlank
    @Schema(description = "내용", example = "test 입니다.")
    private String content;
    @NotBlank
    @Schema(description = "작성자 이메일", example = "test@test.com")
    private String writerEmail;

    @Schema(hidden = true)
    private LocalDateTime createAt;
    @Schema(hidden = true)
    private LocalDateTime updateAt;
    @Schema(hidden = true)
    private int viewCount;
    @Schema(hidden = true)
    private boolean status;
}
