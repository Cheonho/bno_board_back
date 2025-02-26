package com.bno.board_back.mapper;

import com.bno.board_back.dto.object.BoardListView;
import com.bno.board_back.entity.BoardListViewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardListViewMapper extends GenericMapper<BoardListView, BoardListViewEntity> {

    @Named("mapLongToString")
    default String mapLongToString(Long value) {
        return value != null ? String.valueOf(value) : null;
    }

    @Mapping(target = "boardNum", source = "boardNum", qualifiedByName = "mapLongToString")
    BoardListView toDTO(BoardListViewEntity boardListViewEntity);

    BoardListViewMapper INSTANCE = Mappers.getMapper(BoardListViewMapper.class);
}
