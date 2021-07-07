package com.project.board.service.Comment;

import java.util.List;

import com.project.board.Repository.Comment.CommentRepository;
import com.project.board.session.UserInfo;
import com.project.board.util.PagingUtil;
import com.project.board.web.Comment.dto.CommentDto;
import com.project.board.web.Comment.dto.CreateCommentDto;
import com.project.board.web.Comment.dto.DeleteCommentDto;
import com.project.board.web.Comment.dto.ModifyCommentDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final UserInfo userInfo;

    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentDto> findByBoardUid(PagingUtil pagingUtil, Long uid) {
        return commentRepository.findByBOARDUID(pagingUtil, uid);
    }

    // 댓글 작성
    @Transactional
    public void createComment(CreateCommentDto createCommentDto) {
        commentRepository.createComment(createCommentDto);
    }

    // 댓글 수정
    @Transactional
    public Boolean modifyComment(ModifyCommentDto modifyCommentDto) {
        CommentDto commentDto = this.findByCommentUid(modifyCommentDto.getCOMMENT_UID());
        if (userInfo.getUserId().equals(commentDto.getWRITER())) {
            commentRepository.modifyComment(modifyCommentDto);

            return true;
        } else {
            
            return false;
        }
    }

    // 댓글 삭제
    @Transactional
    public Boolean deleteComment(DeleteCommentDto deleteCommentDto) {
        CommentDto commentDto = this.findByCommentUid(deleteCommentDto.getCOMMENT_UID());

        if (userInfo.getUserId().equals(commentDto.getWRITER())) {
            commentRepository.deleteComment(deleteCommentDto);

            return true;
        } else {

            return false;
        }
    }

    // 댓글 UID로 조회
    @Transactional(readOnly = true)
    public CommentDto findByCommentUid(Long comment_uid) {
        return commentRepository.findByCOMMENTUID(comment_uid);
    }

    
    // 댓글 개수 조회
    @Transactional(readOnly = true)
    public Integer countComment(Long board_uid) {
        return commentRepository.countComment(board_uid);
    }

    // 페이징 데이터 가공
    public PagingUtil createPagingData(Long board_uid, String curPageNo) {
        PagingUtil pagingUtil = new PagingUtil();

        // 전체 게시글 개수 세팅
        pagingUtil.setTotal(this.countComment(board_uid));

        // 현재 페이지 세팅
        pagingUtil.setCurPageNo(Integer.parseInt(curPageNo)-1);

        // 페이지당 조회할 개수
        pagingUtil.setCntPerPage(3);

        pagingUtil.offsetVal();

        pagingUtil.calEndPageNo();

        return pagingUtil;
    }
}
