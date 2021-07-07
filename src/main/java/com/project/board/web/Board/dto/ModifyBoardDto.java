package com.project.board.web.Board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ModifyBoardDto {

    // 게시물 UID
    private Long BOARD_UID;

    // 게시물 작성자 IP
    private String WRITER_IP;

    // 게시물 제목
    private String TITLE;

    // 게시물 내용
    private String CONTENT;

    // 게시물 수정 날짜/시간
    private String MODIFY_TIME;

}
