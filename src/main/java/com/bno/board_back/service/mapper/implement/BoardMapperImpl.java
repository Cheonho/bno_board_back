package com.bno.board_back.service.mapper.implement;

import com.bno.board_back.dto.object.Boards;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.service.mapper.BoardMapper;
import com.bno.board_back.utils.TsidUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class BoardMapperImpl implements BoardMapper {

    private final TsidUtil tsidUtil;

    @Override
    public BoardEntity toEntity(Boards boards) {
        if (boards == null) {
            return null;
        }

        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setBoardNum(tsidUtil.getTsid());
        boardEntity.setTitle(boards.getTitle());
        boardEntity.setContent(boards.getContent());
        boardEntity.setWriterEmail(boards.getWriterEmail());
        boardEntity.setCreateAt(LocalDateTime.now());
        boardEntity.setUpdateAt(null);
        boardEntity.setViewCount(0);
        boardEntity.setStatus(true);

        return boardEntity;
    }

    @Override
    public Boards toDTO(BoardEntity boardEntity) {
        return null;
    }

    @Override
    public List<Boards> toDtoList(List<BoardEntity> boardEntities) {
        return List.of();
    }

    @Override
    public List<BoardEntity> toEntityList(List<Boards> boards) {
        return List.of();
    }

    @Override
    public void updateFormDto(Boards boards, BoardEntity boardEntity) {

    }
}
