package com.project.board.web.Board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardDto {

    // 게시물 UID
    private Long BOARD_UID;

    // 게시물 작성자
    private String WRITER;

    // 게시물 작성자 IP
    private String WRITER_IP;

    // 게시물 제목
    private String TITLE;

    // 게시물 내용
    private String CONTENT;

    // 게시물 작성 날짜/시간
    private String CREATE_TIME;

    // 게시물 수정 날짜/시간
    private String MODIFY_TIME;

    // 원글 UID
    private Long ORIGIN_NO;

    // 계층 개수
    private Long LAYER_NO;

    // 계층 깊이
    private Long LAYER_DEPTH;

    // 부모 UID
    private Long PARENT_BOARD_UID;

}
