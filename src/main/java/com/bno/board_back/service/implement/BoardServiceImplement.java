package com.bno.board_back.service.implement;

import com.bno.board_back.dto.object.Boards;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.dto.response.board.PostWriteBoardResponseDto;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.repository.BoardListViewRepository;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.repository.UserRepository;
import com.bno.board_back.service.BoardService;
import com.bno.board_back.service.mapper.BoardMapper;
import com.bno.board_back.utils.TsidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardListViewRepository boardListViewRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final TsidUtil tsidUtil;
    private final BoardMapper boardMapper;

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable) {

        Page<BoardListViewEntity> page;

        try {
            page = boardListViewRepository.findByOrderByCreateAtDesc(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        return GetBoardListResponseDto.sucess(page);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord, Pageable pageable) {

        Page<BoardListViewEntity> boardSearchListViewEntities;

        try {
            boardSearchListViewEntities = boardListViewRepository.findSearch(searchWord, category, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        return GetSearchBoardListResponseDto.success(boardSearchListViewEntities);

    }

    @Override
    public ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(Boards board) {

        boolean checkUser = false;
        System.out.println("----------------------- tsidUtil.getTsid() : " + tsidUtil.getTsid() + "-----------------------");

        try {
            checkUser = userRepository.existsByEmail(board.getWriterEmail());
            if (!checkUser) return ResponseDto.authError();

//            BoardEntity boardEntity = BoardEntity.createBoard(tsidUtil.getTsid(), board.getTitle(), board.getContent(), board.getWriterEmail());
            BoardEntity boardEntity = boardMapper.toEntity(board);

            boardRepository.save(boardEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        return PostWriteBoardResponseDto.success();
    }
}
