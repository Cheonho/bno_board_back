package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.FileDto;
import com.bno.board_back.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<FileDto, FileEntity> {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);
}
