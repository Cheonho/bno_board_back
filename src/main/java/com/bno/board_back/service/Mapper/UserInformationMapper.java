package com.bno.board_back.service.Mapper;

import com.bno.board_back.dto.userDto.UserInformationChangeDto;
import com.bno.board_back.entity.UserEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

public interface UserInformationMapper extends GenericMapper<UserInformationChangeDto, UserEntity> {

    @Named("mapLongToString")
    default String mapLongToString(Long value) {
        return value != null ? String.valueOf(value) : null;
    }
    @Mapping(target = "id", source = "id", qualifiedByName = "mapLongToString")
        // Entity에서 Dto 변환을 수행하는 매퍼 인터페이스의 일부
        // UserDto 응답 정보를 포함해야 하므로 서버에서 가공해서 내려줘야하기 때문에 변환에 적절하다
    UserInformationChangeDto toDTO(UserEntity userEntity) ;

    UserInformationMapper INSTANCE = Mappers.getMapper(UserInformationMapper.class);
}
