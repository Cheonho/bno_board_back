package com.bno.board_back.service.implement;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.repository.BoardListViewRepository;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardListViewRepository boardListViewRepository;

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            boardListViewEntities = boardListViewRepository.findByOrderByCreateAtDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        return GetBoardListResponseDto.sucess(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord) {

        List<BoardListViewEntity> boardSearchListViewEntities = new ArrayList<>();

        try {
            switch (category) {
                case 1:
                    boardSearchListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrWriterNicknameContainsOrderByCreateAtDesc(searchWord, searchWord, searchWord);
                    break;
                case 2:
                    boardSearchListViewEntities = boardListViewRepository.findByWriterNicknameContainsOrderByCreateAtDesc(searchWord);
                    break;
                case 3:
                    boardSearchListViewEntities = boardListViewRepository.findByTitleContainsOrderByCreateAtDesc(searchWord);
                    break;
                case 4:
                    boardSearchListViewEntities = boardListViewRepository.findByContentContainsOrderByCreateAtDesc(searchWord);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }

        return GetSearchBoardListResponseDto.success(boardSearchListViewEntities);

    }
}
