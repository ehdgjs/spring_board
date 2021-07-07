package com.project.board.web.Board;

import com.project.board.service.Board.BoardService;
import com.project.board.session.UserInfo;
import com.project.board.util.ApiResponse;
import com.project.board.web.Board.dto.BoardDto;
import com.project.board.web.Board.dto.CreateBoardDto;
import com.project.board.web.Board.dto.CreateLayerBoardDto;
import com.project.board.web.Board.dto.DeleteBoardDto;
import com.project.board.web.Board.dto.ModifyBoardDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class RestBoardController {

    private final BoardService boardService;
    private final UserInfo userInfo;

    Logger logger = LoggerFactory.getLogger(RestBoardController.class);

    @PostMapping("createBoard")
    public ResponseEntity<?> createBoard(CreateBoardDto createBoardDto) {
        ApiResponse result = null;

        try {

            if (boardService.createBoard(createBoardDto)) {

                result = new ApiResponse(true, "성공", null);

                return ResponseEntity.ok().body(result);

            } else {

                result = new ApiResponse(false, "10분 동안 3개 이상의 게시글을 등록하셨습니다.", null);

                return ResponseEntity.ok().body(result);

            }

        } catch (Exception e) {

            logger.error("/board/createBoard => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), createBoardDto);

            return ResponseEntity.badRequest().body(result);

        }
    }

    @PostMapping("createLayerBoard")
    public ResponseEntity<?> createLayerBoard(CreateLayerBoardDto createLayerBoardDto) {
        ApiResponse result = null;

        try {
            if (boardService.createLayerBoard(createLayerBoardDto) == 1) {

                result = new ApiResponse(true, "성공", null);

                return ResponseEntity.ok().body(result);

            } else if (boardService.createLayerBoard(createLayerBoardDto) == 3) {

                result = new ApiResponse(false, "Depth는 10까지 제한되어있습니다.", null);

                return ResponseEntity.ok().body(result);

            } else {

                result = new ApiResponse(false, "10분 동안 3개 이상의 게시글을 등록하셨습니다.", null);

                return ResponseEntity.ok().body(result);

            }
        } catch (Exception e) {

            logger.error("/board/createLayerBoard => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), createLayerBoardDto);

            return ResponseEntity.badRequest().body(result);

        }
    }

    @PutMapping("modifyBoard")
    public ResponseEntity<?> modifyBoard(ModifyBoardDto modifyBoardDto) {
        ApiResponse result = null;

        try {
            if (boardService.modifyBoard(modifyBoardDto)){

                result = new ApiResponse(true, "성공", null);

                return ResponseEntity.ok().body(result);

            } else {

                result = new ApiResponse(false, "해당 게시글의 비밀번호와 일치하지 않습니다.", null);

                return ResponseEntity.ok().body(result);

            }
        } catch (Exception e) {

            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);

            return ResponseEntity.badRequest().body(result);

        }
    }

    @PostMapping("deleteBoard")
    public ResponseEntity<?> deleteBoard(DeleteBoardDto deleteBoardDto) {
        ApiResponse result = null;

        try {

            if (boardService.deleteBoard(deleteBoardDto)) {

                result = new ApiResponse(true, "성공", null);
    
                return ResponseEntity.ok().body(result);

            } else {

                result = new ApiResponse(false, "해당 게시글의 비밀번호와 일치하지 않습니다.", null);

                return ResponseEntity.ok().body(result);

            }

        } catch (Exception e) {

            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);

            return ResponseEntity.badRequest().body(result);

        }
    }

    @GetMapping("selectAuth")
    public Boolean selectAuth(@RequestParam(value="uid") Long boardUid) {

        BoardDto boardDto = boardService.getBoardByBoardUid(boardUid);
        
        if (boardDto.getWRITER().equals(userInfo.getUserId())) {

            return true;

        } else {

            return false;

        }
    }
    
}
