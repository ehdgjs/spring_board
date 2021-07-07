<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>
<%@include file = "../nav.jsp"%>

    <div>
        <div>
            <h3>게시글 상세</h3>
        </div>
        <div>
            <span>
                <strong>제목</strong> : <strong>${boardInfo.TITLE}</strong>
            </span>
        </div>
        <div>
            <strong><pre>${boardInfo.CONTENT}</pre></strong>
        </div>
<c:if test="${userInfo eq boardInfo.WRITER}">
        <button id=modifyBoardBtn>수정</button>
        <button id="deleteBoardBtn">삭제</button>
</c:if>
        <button id="moveToBackBtn">목록</button>
        <div>
            <br>
            <div>
                <label for="commentContent">댓글 : </label>
                <input type="text" name="commentContent" id="commentContent" placeholder="댓글 내용을 작성해 주세요."/>
                <button id="createComment">작성</button>
            </div>
            <hr>
            <div>
<c:forEach items="${commentInfo}" var="item" varStatus="status">
            <div>
                <div>
                    작성자 : <span>${item.WRITER}</span>
                </div>
                <div>
                    내용 : <span>${item.CONTENT}</span>
                </div>
                <div style="display: none">
                    <input type="hidden" name="commentUid" value="${item.COMMENT_UID}"/>
                    <label for="inputContent">내용 : </label>
                    <input type="text" name="content" value="${item.CONTENT}"/>
                    <button class="modifyComment">수정</button>
                </div>
<c:if test="${item.WRITER eq userInfo}">
                <div>
                    <button class="modifyCommentBtn">댓글 수정</button>
                    <button onclick="deleteComment(${item.COMMENT_UID})">댓글 삭제</button>
                </div>
</c:if>
            </div>
            <hr>
</c:forEach>
<c:forEach begin="${pagingInfo.startPageNo}" end="${pagingInfo.endPageNo}" var="p">
    <c:if test="${p eq pagingInfo.curPageNo+1}">
        <b>${p}</b>
    </c:if>
    <c:if test="${p ne pagingInfo.curPageNo+1}">
        <a style="cursor: pointer;" onclick="movePaging(${p})">${p}</a>
    </c:if>
</c:forEach>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/js/Board/boardDetail.js" ></script>
    <script type="text/javascript" src="/js/nav.js" ></script>

<%@include file = "../footer.jsp"%>