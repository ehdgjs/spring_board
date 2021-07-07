package com.project.board.web.Board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateLayerBoardDto {

    // 게시물 작성자
    private String WRITER;

    // 게시물 작성자 IP
    private String WRITER_IP;

    // 게시물 제목
    private String TITLE;

    // 게시물 내용
    private String CONTENT;

    // 원글 UID
    private Long ORIGIN_NO;

    // 댓글 순서
    private Long LAYER_NO;

    // 계층 깊이
    private Long LAYER_DEPTH;

    // 부모 UID
    private Long PARENT_BOARD_UID;
    
}
