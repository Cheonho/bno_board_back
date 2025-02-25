package com.bno.board_back.dto.object;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteBoards {

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
}
