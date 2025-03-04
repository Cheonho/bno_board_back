package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.AbstractIdDto;
import com.bno.board_back.utils.TsidUtilUseSystem;
import org.mapstruct.*;

import java.util.List;

// before mapping -> generic id
public interface GenericMapper<DTO, Entity> {

    DTO toDTO (Entity entity);
    Entity toEntity (DTO dto);

    List<DTO> toDtoList (List<Entity> entityList);
    List<Entity> toEntityList (List<DTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFormDto (DTO dto, @MappingTarget Entity entity);

    @BeforeMapping
    default void generateBoardNum(DTO dto) {
        if (dto == null) return;

        if (dto instanceof AbstractIdDto) {
            AbstractIdDto abstractDto = (AbstractIdDto) dto;

            if (abstractDto.getBoardNum() == null) {
                abstractDto.setBoardNum(String.valueOf(TsidUtilUseSystem.getTsid()));
            }
        }
    }

    @Named("mapStringToLong")
    default Long mapStringToLong(String value) {
        return value != null ? Long.parseLong(value) : null;
    }

    @Named("mapLongToString")
    default String mapLongToString(Long value) {
        return value != null ? String.valueOf(value) : null;
    }

}
