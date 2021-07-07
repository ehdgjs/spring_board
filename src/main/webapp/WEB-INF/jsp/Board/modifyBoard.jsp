<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>
<%@include file = "../nav.jsp"%>

    <h2>게시물 수정</h2>
    <div>
        <div>
            <label for="inputTitle">제목 : </label>
            <input type="text" name="title" id="inputTitle" value="${boardInfo.TITLE}"/>
        </div>
        <br>
        <div>
            <label for="inputContent">내용 : </label>
            <textarea style="vertical-align: top;" name="content" id="inputContent" cols="30" rows="10">${boardInfo.CONTENT}</textarea>
        </div>
    </div>

    <br/>

    <div>
        <button id="modifyBtn">수정하기</button>
        <button id="backBtn">돌아가기</button>
    </div>

    <script type="text/javascript" src="/js/Board/modifyBoard.js" ></script>
    <script type="text/javascript" src="/js/nav.js" ></script>

<%@include file = "../footer.jsp"%>