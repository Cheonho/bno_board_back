package com.bno.board_back.dto.object;

import com.bno.board_back.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {

    private String email;
    private String password;
    private String userName;
    private String address;
    private String role;

    public Users(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.userName = userEntity.getUserNickname();
        this.address = userEntity.getAddress();
        this.role = userEntity.getRole();
    }
}
