package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.entity.BoardListViewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

// entity mapper 
@Mapper(componentModel = "spring")
public interface BoardListViewMapper extends GenericMapper<BoardListView, BoardListViewEntity> {

    BoardListView toDTO(BoardListViewEntity boardListViewEntity);

    BoardListViewMapper INSTANCE = Mappers.getMapper(BoardListViewMapper.class);
}
