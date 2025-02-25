package com.bno.board_back.service.mapper;

import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.entity.BoardListViewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardListViewMapper extends GenericMapper<BoardListView, BoardListViewEntity> {
    BoardListViewMapper INSTANCE = Mappers.getMapper(BoardListViewMapper.class);
}
