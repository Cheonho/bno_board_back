package com.bno.board_back.dto.userDto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor // 기본 생성자
@Builder

public class UserDto {

    @Id
    private String id ;
    private String email ;
    private String userNickname ;
    private String password ;
    private String address ;
    private String role ;

    public UserDto userNicknameDto(String id, String userNickname) {
        return UserDto.builder()
                .id(id)
                .userNickname(userNickname)
                .build();  // 빌드하여 객체 반환
    }

    public UserDto passwordDto(String id, String password) {
        return UserDto.builder()
                .id(id)
                .password(password)
                .build() ;
    };

}
