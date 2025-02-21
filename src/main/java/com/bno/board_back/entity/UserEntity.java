package com.bno.board_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
@Table(name="users")
public class UserEntity {

    @Id
    private String userId;
    private String userNickname;
    private String password;
    private String email;
    private String tel;
    private String address;
    private String role;
    private String salt;
}
