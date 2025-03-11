package com.bno.board_back.service.implement;

import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.*;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.mapper.BoardUpdateMapper;
import com.bno.board_back.repository.BoardListViewRepository;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.repository.UserRepository;
import com.bno.board_back.service.BoardService;
import com.bno.board_back.mapper.BoardWriteMapper;
import com.bno.board_back.utils.TsidUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImplement.class);

    private final BoardListViewRepository boardListViewRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final TsidUtil tsidUtil;
    private final BoardUpdateMapper boardUpdateMapper;

    public boolean saveBoard(WriteBoards writeBoards) {
        if (writeBoards.getTitle() == null || writeBoards.getTitle().isEmpty()) { return false; }
        if (writeBoards.getContent() == null || writeBoards.getContent().isEmpty()) { return false; }
        if (writeBoards.getWriterEmail() == null || writeBoards.getWriterEmail().isEmpty()) { return false; }

        BoardEntity boardEntity = BoardWriteMapper.INSTANCE.toEntity(writeBoards);

        boardRepository.save(boardEntity);
        return true;
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable) {

        Page<BoardListViewEntity> page;

        try {
            page = boardListViewRepository.findByOrderByCreateAtDesc(pageable);
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return GetBoardListResponseDto.success(page);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord, Pageable pageable) {

        Page<BoardListViewEntity> boardSearchListViewEntities;

        try {
            boardSearchListViewEntities = boardListViewRepository.findSearch(searchWord, category, pageable);
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return GetSearchBoardListResponseDto.success(boardSearchListViewEntities);

    }

    @Override
    public ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(WriteBoards board) {

        boolean checkUser = false;
        boolean checkBoard = false;
        System.out.println("----------------------- tsidUtil.getTsid() : " + tsidUtil.getTsid() + "-----------------------");

        try {
            checkUser = userRepository.existsByEmail(board.getWriterEmail());
            if (!checkUser) return ResponseDto.notFoundUser();

            checkBoard = saveBoard(board);
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        if (checkBoard) {
            return PostWriteBoardResponseDto.success();
        } else {
            return ResponseDto.authError();
        }
    }

    @Override
    public ResponseEntity<? super PutUpdateBoardResponseDto> patchUpdateBoard(UpdateBoards board) {

        boolean checkUser = false;
        BoardEntity updateBoard;

        try{
            checkUser = userRepository.existsByEmail(board.getWriterEmail());
            if (!checkUser) return ResponseDto.notFoundUser();

            String boardNum = board.getBoardNum() != null ? board.getBoardNum() : null;
            updateBoard = boardRepository.findByBoardNumAndWriterEmail(boardNum, board.getWriterEmail());
            if (updateBoard == null) return ResponseDto.notFoundBoard();

            boardUpdateMapper.updateFormDto(board, updateBoard);
            boardRepository.save(updateBoard);
        } catch (Exception e){
            logger.error("error", e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return PutUpdateBoardResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super PatchIncreaseViewCountDto> increaseCount(String boardNum) {

        BoardEntity board;

        try {
            board = boardRepository.findByBoardNum(boardNum);
//            if (board == null) return ResponseDto.notFoundBoard();
            if (board == null) throw new Exception();

            board.setViewCount(board.getViewCount() + 1);
        } catch (Exception e) {
            logger.error("error", e);
            System.out.println("test");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return PatchIncreaseViewCountDto.success();
    }

    @Override
    public ResponseEntity<? super GetDetailBoardResponseDto> getDetailBoard(String boardNum) {

        BoardListViewEntity boardListViewEntity;

        try {
            boardListViewEntity = boardListViewRepository.findByBoardNum(boardNum);
            if (boardListViewEntity == null) return ResponseDto.notFoundBoard();

        }catch (Exception e){
            logger.error("error", e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return GetDetailBoardResponseDto.success(boardListViewEntity);
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoardById(String boardNum) {
        BoardListViewEntity boardListViewEntity;
        try {
            boardListViewEntity = boardListViewRepository.findByBoardNum(boardNum);
            if (boardListViewEntity==null) return ResponseDto.notFoundBoard();
//                    .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//            boardEntity.increaseViewCount();
//            boardListViewRepository.save(boardListViewEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(boardListViewEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBoardById(String boardNum) {
        try {
            BoardEntity boardEntity = boardRepository.findById(boardNum)
                    .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

            boardEntity.setStatus(false);
            boardRepository.save(boardEntity);

            return ResponseDto.resSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

}
