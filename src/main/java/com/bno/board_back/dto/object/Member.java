package com.bno.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private int id;
    private String userId;
    private String password;
    private String userName;
    private String salt;
    private String address;
    private String role;
}
