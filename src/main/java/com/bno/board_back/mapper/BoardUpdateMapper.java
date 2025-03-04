package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardUpdateMapper extends GenericMapper<UpdateBoards, BoardEntity> {

    @Mapping(target = "boardNum", source = "boardNum", qualifiedByName = "mapStringToLong")
    @Mapping(target = "updateAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "viewCount", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateFormDto(UpdateBoards updateBoards, @MappingTarget BoardEntity boardEntity);

    BoardListViewMapper INSTANCE = Mappers.getMapper(BoardListViewMapper.class);
}
