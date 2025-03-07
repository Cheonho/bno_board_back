package com.bno.board_back.dto.userDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id ;
    @NotNull
    private String email ;
    @NotNull
    private String userNickname ;
    @NotNull
    private String address ;
    private String role ;

    }

