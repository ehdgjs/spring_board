package com.project.board.Repository.Comment;

import java.util.List;

import com.project.board.util.PagingUtil;
import com.project.board.web.Comment.dto.CommentDto;
import com.project.board.web.Comment.dto.CreateCommentDto;
import com.project.board.web.Comment.dto.DeleteCommentDto;
import com.project.board.web.Comment.dto.ModifyCommentDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentRepository {
    
    // 댓글 조회
    List<CommentDto>findByBOARDUID(@Param("pagingUtil") PagingUtil pagingUtil, @Param("BOARD_UID") Long BOARD_UID);
    
    // 댓글 생성
    void createComment(CreateCommentDto createCommentDto);

    // 댓글 수정
    void modifyComment(ModifyCommentDto modifyCommentDto);

    // 댓글 삭제
    void deleteComment(DeleteCommentDto deleteCommentDto);

    // 댓글 UID로 조회
    CommentDto findByCOMMENTUID(Long COMMENT_UID);

    // 댓글 개수 조회
    int countComment(Long BOARD_UID);
}
