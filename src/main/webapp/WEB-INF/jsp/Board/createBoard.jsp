<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>
<%@include file = "../nav.jsp"%>

    <div id="createBoard">
        <div>
            <h2>게시글 생성</h2>
        </div>
        <div>
            <label for="inputTitle">제목 : </label>
            <input type="text" name="title" id="inputTitle" placeholder="제목을 입력해주세요"/>
        </div>
        <br>
        <div>
            <label for="inputContent">내용 : </label>
            <textarea style="vertical-align: top;" name="content" id="inputContent" cols="30" rows="10"></textarea>
        </div>
        <div>
            <button id="createSubmitBtn">작성</button>
            <button id="moveToBackBtn">취소</button>
        </div>
    </div>

    <script type="text/javascript" src="/js/Board/createBoard.js" ></script>
    <script type="text/javascript" src="/js/nav.js" ></script>

<%@include file = "../footer.jsp"%>