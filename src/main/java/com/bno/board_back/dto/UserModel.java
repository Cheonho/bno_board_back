package com.bno.board_back.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "member")

public class UserModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String userId ;
    private String userName;
    private String userPw ;
    private String salt ;
    private String address ;
    private String role ;

}
