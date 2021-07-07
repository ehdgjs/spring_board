package com.project.board.web.Comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentDto {
    
    // 댓글 UID
    private Long COMMENT_UID;

    // 게시글 UID
    private Long BOARD_UID;

    // 댓글 작성자
    private String WRITER;
    
    // 댓글 내용
    private String CONTENT;

}
