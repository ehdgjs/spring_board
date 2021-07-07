package com.project.board.web.Comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ModifyCommentDto {

    // 댓글 UID
    private Long COMMENT_UID;

    // 댓글 내용
    private String CONTENT;

}
