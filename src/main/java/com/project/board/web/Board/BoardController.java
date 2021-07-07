package com.project.board.web.Board;

import java.util.List;

import com.project.board.service.Board.BoardService;
import com.project.board.service.Comment.CommentService;
import com.project.board.session.UserInfo;
import com.project.board.util.PagingUtil;
import com.project.board.web.Board.dto.BoardDto;
import com.project.board.web.Comment.dto.CommentDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    
    private final BoardService boardService;
    private final CommentService commentService;
    private final UserInfo userInfo;

    @GetMapping("/boardList.do")
    public String getBoardList(Model model,
        @RequestParam(value = "curPageNo", required = false) String curPageNo,
        @RequestParam(value = "cntPerPage", required = false) String cntPerPage
        ) {
        
        // paging default 설정

        if(curPageNo == null) {
            curPageNo = "1";
        }

        if(cntPerPage == null) {
            cntPerPage = "5";
        }

        PagingUtil pagingUtil = boardService.createPagingData(curPageNo, cntPerPage);

        List<BoardDto> boardList = boardService.getBoardList(pagingUtil);

        model.addAttribute("pagingInfo", pagingUtil);
        model.addAttribute("boardList", boardList);
        model.addAttribute("userInfo", userInfo.getUserId());

        return "Board/boardList";
    }

    @GetMapping("/createBoard.do")
    public String createBoard() {

        return "Board/createBoard";
    }

    @GetMapping("/boardDetail.do")
    public String boardDetail(
        @RequestParam(value= "uid") Long boardUid,
        @RequestParam(value = "curPageNo", required = false) String curPageNo,
        Model model) {
            
            if (curPageNo == null) {
                curPageNo = "1";
            }

            PagingUtil pagingUtil = commentService.createPagingData(boardUid, curPageNo);

            BoardDto boardInfo = boardService.getBoardByBoardUid(boardUid);

            List<CommentDto> commentInfo = commentService.findByBoardUid(pagingUtil, boardUid);

            model.addAttribute("pagingInfo", pagingUtil);
            model.addAttribute("boardInfo", boardInfo);
            model.addAttribute("commentInfo", commentInfo);
            model.addAttribute("userInfo", userInfo.getUserId());

            return "Board/boardDetail";
    }

    @GetMapping("/modifyBoard.do")
    public String modifyBoard(@RequestParam(value="uid") Long boardUid, Model model) {
        
        // board_uid 를 통한 게시글 조회
        BoardDto boardInfo = boardService.getBoardByBoardUid(boardUid);

        model.addAttribute("boardInfo", boardInfo);
        model.addAttribute("userInfo", userInfo.getUserId());

        return "Board/modifyBoard";
    }

    @GetMapping("/createLayerBoard.do")
    public String createLayerBoard(@RequestParam(value = "uid") Long boardUid, Model model) {

        model.addAttribute("userInfo", userInfo.getUserId());

        return "Board/createLayerBoard";
    }
}
