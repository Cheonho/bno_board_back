package com.bno.board_back.dto.userDto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public UserDto userNicknameDto(Long id, String userNickname) {
        return UserDto.builder()
                .id(id)
                .userNickname(userNickname)
                .build();  // 빌드하여 객체 반환
    }

    public UserDto passwordDto(Long id, String password) {
        return UserDto.builder()
                .id(id)
                .password(password)
                .build() ;
    };

}
