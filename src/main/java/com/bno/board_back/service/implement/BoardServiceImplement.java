package com.bno.board_back.service.implement;

import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.*;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.entity.FileEntity;
import com.bno.board_back.exception.CustomException;
import com.bno.board_back.mapper.BoardUpdateMapper;
import com.bno.board_back.repository.BoardListViewRepository;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.repository.FileRepository;
import com.bno.board_back.repository.UserRepository;
import com.bno.board_back.service.BoardService;
import com.bno.board_back.mapper.BoardWriteMapper;
import com.bno.board_back.service.FileService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.bno.board_back.common.ResponseMessage.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImplement.class);

    private final BoardListViewRepository boardListViewRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final FileService fileService;

    private final TsidUtil tsidUtil;
    private final BoardUpdateMapper boardUpdateMapper;
    private final FileRepository fileRepository;

    public BoardEntity saveBoard(WriteBoards writeBoards) {
        if (writeBoards.getTitle() == null || writeBoards.getTitle().isEmpty()) { return null; }
        if (writeBoards.getContent() == null || writeBoards.getContent().isEmpty()) { return null; }
        if (writeBoards.getWriterEmail() == null || writeBoards.getWriterEmail().isEmpty()) { return null; }

        BoardEntity boardEntity = BoardWriteMapper.INSTANCE.toEntity(writeBoards);

        return boardRepository.save(boardEntity);
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

    @Transactional
    @Override
    public ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(WriteBoards board, List<MultipartFile> files) {

        boolean checkUser;
        String fileMessage = "";
        BoardEntity newBoard;

        try {
            checkUser = userRepository.existsByEmail(board.getWriterEmail());
            if (!checkUser) return ResponseDto.notFoundUser();

            newBoard = saveBoard(board);

            if (files != null && newBoard != null) {
                fileMessage = fileService.fileUpload(files, newBoard.getBoardNum());  // 파일 업로드 서비스 호출

                if (!fileMessage.equals(SUCCESS)) {
                    throw new CustomException(fileMessage, fileMessage, "BadRequest", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
            throw new CustomException(INVALID_PASSWORD, INVALID_PASSWORD, "BadRequest", HttpStatus.BAD_REQUEST);
        }

        return PostWriteBoardResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super PutUpdateBoardResponseDto> putUpdateBoard(UpdateBoards board, List<MultipartFile> files, List<String> deleteIdList) {

        boolean checkUser = false;
        String fileMessage = "";
        BoardEntity updateBoard;

        try{
            checkUser = userRepository.existsByEmail(board.getWriterEmail());
            if (!checkUser) throw new CustomException(USER_NOT_FOUND, USER_NOT_FOUND, "NotFound", HttpStatus.NOT_FOUND);

            String boardNum = board.getBoardNum();
            if (boardNum == null) throw new CustomException(INVALID_PASSWORD, INVALID_PASSWORD, "BadRequest", HttpStatus.BAD_REQUEST);

            updateBoard = boardRepository.findByBoardNumAndWriterEmail(boardNum, board.getWriterEmail());
            if (updateBoard == null) throw new CustomException(NOT_EXISTED_BOARD, NOT_EXISTED_BOARD, "NotFound", HttpStatus.NOT_FOUND);

            boardUpdateMapper.updateFormDto(board, updateBoard);
            boardRepository.save(updateBoard);

            if (deleteIdList != null && !deleteIdList.isEmpty()) {
                String isDelete = fileService.deleteFile(boardNum, deleteIdList);
                if (!isDelete.equals(SUCCESS)) {throw new CustomException(DATABASE_ERROR, DATABASE_ERROR, "DataBaseError", HttpStatus.INTERNAL_SERVER_ERROR);}
            }

            if (files != null) {
                fileMessage = fileService.fileUpload(files, board.getBoardNum());

                if (!fileMessage.equals(SUCCESS)) {
                    throw new CustomException(fileMessage, fileMessage, "BadRequest", HttpStatus.BAD_REQUEST);
                }
            }
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

    @Transactional
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoardById(String boardNum) {
        BoardListViewEntity boardListViewEntity;
        List<FileEntity> fileEntity;
        try {
            boardListViewEntity = boardListViewRepository.findByBoardNum(boardNum);
            if (boardListViewEntity==null) return ResponseDto.notFoundBoard();

            fileEntity = fileService.fileList(boardNum);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(boardListViewEntity, fileEntity);
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseDto> deleteBoardById(String boardNum) {
        try {
            BoardEntity boardEntity = boardRepository.findById(boardNum)
                    .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

            List<FileEntity> fileEntity = fileRepository.findAllByBoardNumAndStatusTrue(boardNum);

            boardEntity.setStatus(false);
            boardRepository.save(boardEntity);

            if (fileEntity != null) {
                List<String> fileIdList = fileEntity.stream().map(FileEntity::getId).collect(Collectors.toList());
                fileService.deleteFile(boardNum, fileIdList);
            }

            return ResponseDto.resSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

}
