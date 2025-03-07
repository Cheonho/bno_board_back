package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.entity.BoardEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", config = GenericMapper.class)
@Mapper(componentModel = "spring")
public interface BoardWriteMapper extends GenericMapper<WriteBoards, BoardEntity> {

    @Mapping(target = "boardNum", source = "boardNum", qualifiedByName = "mapStringToLong")
    @Mapping(target = "createAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "viewCount", expression = "java(0)")
    @Mapping(target = "status", expression="java(true)")
    BoardEntity toEntity(WriteBoards writeBoards);

    BoardWriteMapper INSTANCE = Mappers.getMapper(BoardWriteMapper.class);
}
