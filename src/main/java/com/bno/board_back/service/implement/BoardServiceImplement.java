package com.bno.board_back.service.implement;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardResponseDto;
import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {


    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoardById(Long boardNum) {
        BoardEntity boardEntity;
        try {
            boardEntity = boardRepository.findByBoardNumAndStatusTrue(boardNum)
                    .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }
        return GetBoardResponseDto.success(boardEntity);
    }

        @Override
        public ResponseEntity<ResponseDto> deleteBoardById(Long boardNum) {
            try {
                BoardEntity boardEntity = boardRepository.findById(boardNum)
                        .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

                boardEntity.setStatus(false);
                boardRepository.save(boardEntity);

                return ResponseDto.success();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseDto.databseError();
            }
        }



    }
