package com.bno.board_back.service.Mapper;

import com.bno.board_back.dto.userDto.JoinResponseDto;
import com.bno.board_back.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserJoinMapper extends GenericMapper<JoinResponseDto, UserEntity> {

    @Named("mapLongToString")
    default String mapLongToString(Long value) {
        return value != null ? String.valueOf(value) : null ;
    }

    JoinResponseDto toDTO(UserEntity userEntity) ;

    UserJoinMapper INSTANCE = Mappers.getMapper(UserJoinMapper.class) ;

}
