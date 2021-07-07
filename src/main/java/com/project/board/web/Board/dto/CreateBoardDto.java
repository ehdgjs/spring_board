package com.project.board.web.Board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateBoardDto {
    
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

    // 계층 개수
    private Long LAYER_NO;

    // 계층 깊이
    private Long LAYER_DEPTH;
    
}
