package com.bno.board_back.service.Mapper;

import com.bno.board_back.dto.userDto.LoginResponseDto;
import com.bno.board_back.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserLoginMapper extends GenericMapper<LoginResponseDto, UserEntity> {

    @Named("mapLongToString")
    default String mapLongToString(Long value) {
        return value != null ? String.valueOf(value) : null;
    }

    // Entity에서 Dto 변환을 수행하는 매퍼 인터페이스의 일부
    // LoginResponseDto는 응답 정보를 포함해야 하므로 서버에서 가공해서 내려줘야하기 때문에 변환에 적절하다
    LoginResponseDto toDTO(UserEntity userEntity) ;

    UserLoginMapper INSTANCE = Mappers.getMapper(UserLoginMapper.class);
}
