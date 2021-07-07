<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div>
<c:choose>
    <c:when test="${userInfo eq null}">
        <button id="loginBtn">로그인</button>
    </c:when>
    <c:otherwise>
        <button id="logoutBtn">로그아웃</button>
        <button id="mypageBtn">마이페이지</button>
    </c:otherwise>
</c:choose>
</div>