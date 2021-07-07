package com.project.board.web.Comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DeleteCommentDto {
    
    // 댓글 UID
    private Long COMMENT_UID;

}
