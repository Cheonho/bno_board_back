package com.bno.board_back.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApitokendataDto {

    private String id ;
    private String email ;
    private String userNickname ;
    private String address ;
    private String role ;
    private boolean otpEnabled  ;
}

