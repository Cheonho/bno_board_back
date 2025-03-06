package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentUpdateMapper extends GenericMapper<Comment, CommentEntity> {

    @Mapping(target = "commentNum", source = "commentNum", ignore = true)
    @Mapping(target = "boardNum", ignore = true)
    @Mapping(target = "writerEmail", ignore = true)
    @Mapping(target = "parentNum", ignore = true)
    @Mapping(target = "updateAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateFormDto(Comment comment, @MappingTarget CommentEntity commentEntity);

    CommentUpdateMapper INSTANCE = Mappers.getMapper(CommentUpdateMapper.class);


}
