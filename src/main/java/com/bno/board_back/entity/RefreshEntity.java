package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refreshtoken")
@Entity(name = "refreshtoken")
@Builder

public class RefreshEntity {

    @Id
    private String id ;
    private String email ;
    private String refresh ; // 재토큰
    private long expirationTime ; // 만료
}
