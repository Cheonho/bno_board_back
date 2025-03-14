package com.bno.board_back.dto.userDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CheckEmailNicknameDto {
    @NotNull
    private Boolean isAvailable ;


}
