package com.bno.board_back.dto.userDto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserInformationChangeDto {
    private String id ;
    private String userNickname ;
    @Size(min = 8, message = "비밀번호는 최소 8자 이상,")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
            message = "대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password ;
    private String nowpassword ;
    private String checkpassword ;
    private String address ;
}
