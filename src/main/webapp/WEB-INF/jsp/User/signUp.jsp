<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>

    <div>
        <div>
            <h2>회원가입</h2>
        </div>
        <div>
            <label for="inputId">아이디 : </label>
            <input type="text" name="id" id="inputId">
        </div>
        <div>
            <label for="inputPassword">비밀번호 : </label>
            <input type="password" name="password" id="inputPassword">
        </div>
        <div>
            <label for="inputName">이름 : </label>
            <input type="text" name="name" id="inputName">
        </div>
        <div>
            <label for="inputNickname">닉네임 : </label>
            <input type="text" name="nickname" id="inputNickname">
        </div>
        <button id="signUnBtn">가입</button>
        <button id="moveToBackBtn">취소</button>
    </div>

    <script type="text/javascript" src="/js/User/signUp.js" ></script>

<%@include file = "../footer.jsp"%>