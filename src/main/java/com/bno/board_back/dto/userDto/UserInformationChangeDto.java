package com.bno.board_back.dto.userDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserInformationChangeDto {
    private String id ;
    private String userNickname ;
    private String password ;
    private String nowpassword ;
    private String address ;
    private boolean otpEnabled ;
}
