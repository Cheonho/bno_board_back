package com.bno.board_back.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RefreshTokenDto {

    private String id ;
    private String email ;
    private String refresh ; // 재토큰
    private long expirationTime ; // 만료
}


