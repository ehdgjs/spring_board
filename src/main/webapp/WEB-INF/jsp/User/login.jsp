<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>

    <div>
        <div>
            <h2>로그인</h2>
        </div>
        <div>
            <label for="inputId">아이디 : </label>
            <input type="text" name="id" id="inputId"/>
        </div>
        <div>
            <label for="inputPassword">비밀번호 : </label>
            <input type="password" name="password" id="inputPassword">
        </div>
        <button id="loginBtn">로그인</button>
        <button id="signUpBtn">회원가입</button>
    </div>

    <script type="text/javascript" src="/js/User/login.js" ></script>

<%@include file = "../footer.jsp"%>