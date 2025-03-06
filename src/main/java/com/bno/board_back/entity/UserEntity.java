package com.bno.board_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.envers.Audited;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Audited
@AllArgsConstructor
@Table(name = "users")
@Builder

public class UserEntity {

    @Id
    private Long id ;
    @NotNull
    private String email ;
    @NotNull
    private String userNickname ;
    @NotNull
    private String password ;
    @NotNull
    private String address ;
    private String role ;
    private String isAvailable ;


}

