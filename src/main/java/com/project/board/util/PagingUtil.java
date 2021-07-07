package com.project.board.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PagingUtil {
    
    // 현재 페이지 번호
    private int curPageNo;

    // 시작 페이지 번호
    private int startPageNo = 1;

    // 끝 페이지 번호
    private int endPageNo;

    // 페이지 당 조회할 글 개수
    private int cntPerPage;

    // 총 개수
    private int total;

    // SQL offset 변수
    private int offsetVal;

    public void calEndPageNo() {
        setEndPageNo((int)Math.ceil((double)this.total / (double)this.cntPerPage));
    }

    public void offsetVal() {
        setOffsetVal(curPageNo * cntPerPage);
    }

}
