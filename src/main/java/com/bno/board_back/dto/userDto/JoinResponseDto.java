package com.bno.board_back.dto.userDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id ;
    private String email ;
    private String userNickname ;
    private String address ;
    private String role ;

    }

