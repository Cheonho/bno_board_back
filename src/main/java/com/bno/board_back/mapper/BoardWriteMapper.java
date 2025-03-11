package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.entity.BoardEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardWriteMapper extends GenericMapper<WriteBoards, BoardEntity> {

    @Mapping(target = "createAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateAt", expression = "java((java.time.LocalDateTime) null)")
    @Mapping(target = "viewCount", expression = "java(0)")
    @Mapping(target = "status", expression="java(true)")
    BoardEntity toEntity(WriteBoards writeBoards);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "viewCount", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateFormDto(WriteBoards dto, @MappingTarget BoardEntity entity);

    BoardWriteMapper INSTANCE = Mappers.getMapper(BoardWriteMapper.class);
}
