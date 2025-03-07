package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.Path;
import com.bno.board_back.entity.PathEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PathMapper extends GenericMapper<Path, PathEntity> {

    PathMapper INSTANCE = Mappers.getMapper(PathMapper.class);
}
