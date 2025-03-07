package com.bno.board_back.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApitokendataDto {

    private Long id ;
    private String email ;
    private String userNickname ;
    private String role ;
}
