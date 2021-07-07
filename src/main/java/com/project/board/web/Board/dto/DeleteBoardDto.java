package com.project.board.web.Board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DeleteBoardDto {
    
    // 게시물 UID
    private Long BOARD_UID;

    // 원글 UID
    private Long ORIGIN_NO;

    // 계층 깊이
    private Long LAYER_DEPTH;
    
}
