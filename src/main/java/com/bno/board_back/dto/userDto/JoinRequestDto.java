package com.bno.board_back.dto.userDto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

// 클라이언트 -> 서버
public class JoinRequestDto {

    @Id
    private String id ;
    @Email(message = "유효한 이메일을 입력하세요.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @NotNull
    private String email ;
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @NotNull
    private String userNickname ;
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
            message = "비밀번호는 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @NotNull
    private String password ;
    @NotNull
    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    private String address ;
    private String role;

}
