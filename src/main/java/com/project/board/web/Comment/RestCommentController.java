package com.project.board.web.Comment;

import com.project.board.service.Comment.CommentService;
import com.project.board.session.UserInfo;
import com.project.board.util.ApiResponse;
import com.project.board.web.Comment.dto.CreateCommentDto;
import com.project.board.web.Comment.dto.DeleteCommentDto;
import com.project.board.web.Comment.dto.ModifyCommentDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class RestCommentController {

    private final CommentService commentService;
    private final UserInfo userInfo;

    Logger logger = LoggerFactory.getLogger(RestCommentController.class);
    
    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(CreateCommentDto createCommentDto) {
        ApiResponse result = null;

        try {
            createCommentDto.setWRITER(userInfo.getUserId());

            commentService.createComment(createCommentDto);

            result = new ApiResponse(true, "성공", null);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            logger.error("/createComment => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), createCommentDto);

            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping("/modifyComment")
    public ResponseEntity<?> modifyComment(ModifyCommentDto modifyCommentDto) {
        ApiResponse result = null;

        try {
            if (commentService.modifyComment(modifyCommentDto)) {
                result = new ApiResponse(true, "성공", null);

                return ResponseEntity.ok().body(result);
            } else {
                result = new ApiResponse(false, "수정할 권한이 없습니다.", null);

                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            logger.error("/modifyComment => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), modifyCommentDto);

            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/deleteComment")
    public ResponseEntity<?> deleteComment(DeleteCommentDto deleteCommentDto) {
        ApiResponse result = null;

        try {
            if (commentService.deleteComment(deleteCommentDto)) {
                result = new ApiResponse(true, "성공", null);

                return ResponseEntity.ok().body(result);
            } else {
                result = new ApiResponse(false, "수정할 권한이 없습니다.", null);

                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            logger.error("/modifyComment => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), deleteCommentDto);

            return ResponseEntity.badRequest().body(result);
        }
    }

}
