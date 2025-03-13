package com.bno.board_back.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
// 클라이언트가 서버로 요청을 보낼 때 사용하는 DTO
// 사용자가 입력한 정보를 담아 서버로 전송하는 역할을 함
public class LoginRequestDto {
    private String email ;
    private String password ;
}
