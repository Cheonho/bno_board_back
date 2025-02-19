package com.bno.board_back.service.implement;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.repository.BoardListViewRepository;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardListViewRepository boardListViewRepository;

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable) {

        Page<BoardListViewEntity> page;

        try {
            page = boardListViewRepository.findByOrderByBoardNumDesc(pageable);
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
            switch (category) {
                case 1:
                    boardSearchListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrWriterNicknameContainsOrderByCreateAtDesc(searchWord, searchWord, searchWord, pageable);
                    break;
                case 2:
                    boardSearchListViewEntities = boardListViewRepository.findByWriterNicknameContainsOrderByCreateAtDesc(searchWord, pageable);
                    break;
                case 3:
                    boardSearchListViewEntities = boardListViewRepository.findByTitleContainsOrderByCreateAtDesc(searchWord, pageable);
                    break;
                case 4:
                    boardSearchListViewEntities = boardListViewRepository.findByContentContainsOrderByCreateAtDesc(searchWord, pageable);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        return GetSearchBoardListResponseDto.success(boardSearchListViewEntities);

    }
}
