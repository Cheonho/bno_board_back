package com.bno.board_back.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

// 서버가 클라이언트에 요청을 보낼때 사용하는 DTO
// 토큰, 사용자 정보, 권한 정보를 포함해서 반환
public class LoginResponseDto {
    private String id;
    private String email;
    private String userNickname;
    private String role;
    private boolean otpEnabled;

    public static LoginResponseDto toLoginDto(UserDto userEntity) { // Entity → DTO 변환
        LoginResponseDto loginDto = new LoginResponseDto();
        loginDto.setId(userEntity.getId());
        loginDto.setEmail(userEntity.getEmail());
        loginDto.setUserNickname(userEntity.getUserNickname());
        loginDto.setRole(userEntity.getRole());
        return loginDto;
    }
}
