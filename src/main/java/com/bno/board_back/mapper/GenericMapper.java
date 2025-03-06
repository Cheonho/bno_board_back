package com.bno.board_back.mapper;
import org.mapstruct.*;

import java.util.List;

public interface GenericMapper<DTO, Entity> {

    DTO toDTO (Entity entity);
    Entity toEntity (DTO dto);

    List<DTO> toDtoList (List<Entity> entityList);
    List<Entity> toEntityList (List<DTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFormDto (DTO dto, @MappingTarget Entity entity);

    @Named("mapStringToLong")
    default Long mapStringToLong(String value) {
        return value != null ? Long.parseLong(value) : null;
    }

    @Named("mapLongToString")
    default String mapLongToString(Long value) {
        return value != null ? String.valueOf(value) : null;
    }

}