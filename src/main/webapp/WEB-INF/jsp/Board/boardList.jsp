<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>
<%@include file = "../nav.jsp"%>

    <div>
        <h2>게시글</h2>
    </div>
    <div>
        <table>
            <thead>
                <th>제목</th>
                <th>작성자</th>
                <th>작성시간</th>
                <th>수정시간</th>
                <th><button id="createBtn">글 작성</button></th>
                <th>
                    <select name="cntPerPage" id="selCntPerPage" onchange="selCntPerPage(${pagingInfo.curPageNo+1})">
                        <option value="5"
<c:if test="${pagingInfo.cntPerPage == 5}">selected</c:if>
                        >5개</option>
                        <option value="10"
<c:if test="${pagingInfo.cntPerPage == 10}">selected</c:if>
                        >10개</option>
                        <option value="20"
<c:if test="${pagingInfo.cntPerPage == 20}">selected</c:if>
                        >20개</option>
                    </select>
                </th>
            </thead>
            <tbody>
<c:forEach items="${boardList}" var="board" varStatus="status">
                <tr>
                    <td>
<c:if test="${board.LAYER_NO != 0}">
<c:forEach begin="1" end="${board.LAYER_DEPTH}" var="num">
                        &nbsp;
</c:forEach>
                        └
</c:if>
                        ${board.TITLE}
                    </td>
                    <td>${board.WRITER}</td>
                    <td>${board.CREATE_TIME}</td>
                    <td>
<c:choose>
    <c:when test="${board.MODIFY_TIME ne null}">
                        ${board.MODIFY_TIME}
    </c:when>
    <c:otherwise>
                        X
    </c:otherwise>
</c:choose>
                    </td>
                    <td>
                        <button onclick="detailBoard(${board.BOARD_UID})">상세보기</button>
                    </td>
                    <td>
                        <button onclick="createLayerBoard(${board.BOARD_UID})">답글작성</button>
                    </td>
                </tr>
</c:forEach>
            </tbody>
        </table>
    </div>
    <div>
<c:forEach begin="${pagingInfo.startPageNo}" end="${pagingInfo.endPageNo}" var="p">
    <c:if test="${p eq pagingInfo.curPageNo+1}">
        <b>${p}</b>
    </c:if>
    <c:if test="${p ne pagingInfo.curPageNo+1}">
        <a style="cursor:pointer;" onclick="moveToPaging(${p})">${p}</a>
    </c:if>
</c:forEach>
    </div>

    <script type="text/javascript" src="/js/Board/boardList.js" ></script>
    <script type="text/javascript" src="/js/nav.js" ></script>

<%@include file = "../footer.jsp"%>