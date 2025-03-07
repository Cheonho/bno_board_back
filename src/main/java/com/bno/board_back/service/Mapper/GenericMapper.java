package com.bno.board_back.service.Mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface GenericMapper<DTO, Entity> {

    DTO toDTO (Entity entity);
    Entity toEntity (DTO dto);

    List<DTO> toDtoList (List<Entity> entityList);
    List<Entity> toEntityList (List<DTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFormDto (DTO dto, @MappingTarget Entity entity);
}
