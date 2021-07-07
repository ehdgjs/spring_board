package com.project.board.service.Board;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.board.Repository.Board.BoardRepository;
import com.project.board.session.UserInfo;
import com.project.board.util.PagingUtil;
import com.project.board.util.UserIpUtil;
import com.project.board.web.Board.dto.BoardDto;
import com.project.board.web.Board.dto.CreateBoardDto;
import com.project.board.web.Board.dto.CreateLayerBoardDto;
import com.project.board.web.Board.dto.DeleteBoardDto;
import com.project.board.web.Board.dto.ModifyBoardDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
    
    private final BoardRepository boardRepository;
    private final UserInfo userInfo;
    Logger logger = LoggerFactory.getLogger(BoardService.class);
    
    Date date = new Date(System.currentTimeMillis());
    String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

    // 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getBoardList(PagingUtil pagingUtil) {
        return boardRepository.getBoardList(pagingUtil);
    }

    // 게시글 생성 IP확인
    @Transactional(readOnly = false)
    public Boolean createBoard(CreateBoardDto createBoardDto) {
        
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        //회원 IP 조회
        String userIp = UserIpUtil.getIp(req);


        if (checkCreateCondition(userIp)) {

            createBoardDto.setWRITER(userInfo.getUserId());
            createBoardDto.setWRITER_IP(userIp);

            boardRepository.createBoard(createBoardDto);
            
            boardRepository.updateOriginBoard(createBoardDto.getORIGIN_NO());
            
            return true;

        } else {

            return false;

        }

    }

    // 답글 생성
    public int createLayerBoard(CreateLayerBoardDto createLayerBoardDto) {

        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        // 회원 IP 조회
        String userIp = UserIpUtil.getIp(req);

        if (checkCreateCondition(userIp)) {

            BoardDto parentBoard = getBoardByBoardUid(createLayerBoardDto.getORIGIN_NO());

            if (parentBoard.getLAYER_DEPTH() == 10) {
                // Depth 10 제한
                return 3;
            }

            Long parent_uid = createLayerBoardDto.getORIGIN_NO();

            createLayerBoardDto.setWRITER_IP(userIp);
            createLayerBoardDto.setWRITER(userInfo.getUserId());
            createLayerBoardDto.setORIGIN_NO(parentBoard.getORIGIN_NO());
            createLayerBoardDto.setLAYER_NO(parentBoard.getLAYER_NO()+1);
            createLayerBoardDto.setLAYER_DEPTH(parentBoard.getLAYER_DEPTH()+1);
            createLayerBoardDto.setPARENT_BOARD_UID(parent_uid);

            boardRepository.createLayerBoard(createLayerBoardDto);

            return 1;

        } else {

            // 동일한 IP 3번 제한
            return 2;

        }
    }

    // 게시글 수정
    @Transactional(readOnly = false)
    public Boolean modifyBoard(ModifyBoardDto modifyBoardDto) {

        if (this.getBoardByBoardUid(modifyBoardDto.getBOARD_UID()).getWRITER().equals(userInfo.getUserId())) {
            // 게시글 작성자가 일치 할 경우

            boardRepository.modifyBoard(modifyBoardDto);

            return true;

        } else {
            // 게시글 작성자가 불일치 할 경우

            return false;
        }
    }

    // 게시글 삭제
    @Transactional(readOnly = false)
    public Boolean deleteBoard(DeleteBoardDto deleteBoardDto) {

        if (this.getBoardByBoardUid(deleteBoardDto.getBOARD_UID()).getWRITER().equals(userInfo.getUserId())) {
            // 게시글 작성자가 일치 할 경우



            boardRepository.deleteBoard(deleteBoardDto.getBOARD_UID());
            
            return true;

        } else {
            // 게시글 작성자가 불일치 할 경우

            return false;

        }

    }

    // 자식 게시글 삭제
    @Transactional
    public void deleteChildBoard(Long uid) {
        
        // uid가 parent_board_uid인 게시글 찾기

        // 반복문을 통해 자식 게시글 찾기

    }

    // board_uid를 통한 게시글 조회
    @Transactional(readOnly = true)
    public BoardDto getBoardByBoardUid(Long uid) {

        return boardRepository.getBoardByBoardUid(uid);

    }

    // 동일한 IP 10분 이내 3회이상 글 등록 확인
    public Boolean checkCreateCondition(String userIp) {
        int count = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        List<BoardDto> boardListByIp = boardRepository.getBoardListByIp(userIp);

        for (BoardDto el : boardListByIp) {
            Date elDate = null;
            long elTime = 12345678910L;

            try {

                elDate = format.parse(el.getCREATE_TIME());

            } catch (ParseException e) {

                e.printStackTrace();

            }
            
            elTime = elDate.getTime();
        
            long nowTime = date.getTime();

            // 분으로 나누기
            long minute = (nowTime - elTime) / 60000;

            if (minute <= 10) {

                count++;

            }
        }

        // 3회 이상이면 false
        if (count >= 3) {

            return false;

        } else {

            return true;

        }
    }

    // 페이징 데이터 가공
    @Transactional(readOnly = true)
    public PagingUtil createPagingData(String curPageNo, String cntPerPage) {

        PagingUtil pagingUtil = new PagingUtil();
        

        // 전체 게시글 개수 세팅
        pagingUtil.setTotal(this.countBoard());

        // 현재 페이지 세팅
        pagingUtil.setCurPageNo(Integer.parseInt(curPageNo)-1);

        // 페이지당 조회할 개수
        pagingUtil.setCntPerPage(Integer.parseInt(cntPerPage));

        pagingUtil.offsetVal();

        pagingUtil.calEndPageNo();

        return pagingUtil;
    }

    // 게시글 개수 조회
    @Transactional(readOnly = true)
    public int countBoard() {

        return boardRepository.findBoardCnt();

    }
}
