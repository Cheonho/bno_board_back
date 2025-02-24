package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="users")
@Table(name="users")
public class UserEntity {

    @Id
    private Long id;
    private String email;
    private String userNickname;
    private String password;
    private String address;
    private String role;
    private boolean status;
}
