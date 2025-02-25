package com.bno.board_back.mapper.implement;

import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.mapper.BoardWriteMapper;
import com.bno.board_back.utils.TsidUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class BoardMapperImpl implements BoardWriteMapper {

    private final TsidUtil tsidUtil;

    @Override
    public BoardEntity toEntity(WriteBoards boards) {
        if (boards == null) {
            return null;
        }

        BoardEntity boardEntity = new BoardEntity();

        if(boards.getBoardNum() == null) {
            boardEntity.setBoardNum(tsidUtil.getTsid());
        } else {
            boardEntity.setBoardNum(boards.getBoardNum());
        }
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
    public WriteBoards toDTO(BoardEntity boardEntity) {
        if (boardEntity == null) {
            return null;
        }

        WriteBoards boards = new WriteBoards();

        boards.setBoardNum(boardEntity.getBoardNum());
        boards.setTitle(boardEntity.getTitle());
        boards.setContent(boardEntity.getContent());
        boards.setWriterEmail(boardEntity.getWriterEmail());

        return boards;
    }

    @Override
    public List<WriteBoards> toDtoList(List<BoardEntity> boardEntities) {
        return List.of();
    }

    @Override
    public List<BoardEntity> toEntityList(List<WriteBoards> boards) {
        return List.of();
    }

    @Override
    public void updateFormDto(WriteBoards boards, BoardEntity boardEntity) {}
}
