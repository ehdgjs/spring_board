package com.project.board.Repository.Board;

import java.util.List;

import com.project.board.util.PagingUtil;
import com.project.board.web.Board.dto.BoardDto;
import com.project.board.web.Board.dto.CreateBoardDto;
import com.project.board.web.Board.dto.CreateLayerBoardDto;
import com.project.board.web.Board.dto.ModifyBoardDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
    
    // 게시글 목록 조회
    List<BoardDto> getBoardList(PagingUtil pagingUtil);

    // 게시글 등록
    Long createBoard(CreateBoardDto createBoardDto);

    // 답글 등록
    void createLayerBoard(CreateLayerBoardDto createLayerBoardDto);

    // 원글 origin_no 수정
    void updateOriginBoard(Long ORIGIN_NO);

    // 게시글 수정
    void modifyBoard(ModifyBoardDto modifyBoardDto);

    // 게시글 삭제
    void deleteBoard(Long board_uid);

    // board_uid를 통한 게시글 조회
    BoardDto getBoardByBoardUid(Long board_uid);

    // IP를 통한 게시글 조회
    List<BoardDto> getBoardListByIp(String userIp);

    // 게시글 개수 검색
    int findBoardCnt();

}
