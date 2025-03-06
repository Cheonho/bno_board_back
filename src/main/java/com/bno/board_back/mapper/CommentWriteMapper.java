package com.bno.board_back.mapper;


import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentWriteMapper extends GenericMapper<Comment, CommentEntity>{

    @Mapping(target = "commentNum", source = "commentNum")
    @Mapping(target = "boardNum")
    @Mapping(target = "parentNum")
    @Mapping(target = "createAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", ignore = true)
    CommentEntity toEntity(Comment comment);

    CommentWriteMapper INSTANCE = Mappers.getMapper(CommentWriteMapper.class);
    
}
