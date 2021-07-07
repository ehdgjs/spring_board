<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file = "../header.jsp"%>
<%@include file = "../nav.jsp"%>

<div>
    <div>
        <div>
            <h3>회원 수정</h3>
            <div>
                <div id="pwChanger" style="display:none;">
                    <div>
                        <label for="inputNewPw">새 비밀 번호 : </label>
                        <input type="password" name="newPw" id="inputNewPw">
                    </div>
                    <div>
                        <label for="inputNewPwConfirm">새 비밀 번호 확인 : </label>
                        <input type="password" name="newPwConfirm" id="inputNewPwConfirm">
                    </div>
                    <div>
                        <button id="modifyPwBtn">수정</button>
                    </div>
                    <br>
                </div>
            </div>
            <div>
                <div id="nameChanger" style="display:none;">
                    <div>
                        <label for="inputNewName">새 이름 : </label>
                        <input type="text" name="newName" id="inputNewName">
                    </div>
                    <div>
                        <button id="modifyNameBtn">수정</button>
                    </div>
                    <br>
                </div>
            </div>
            <div>
                <div id="nicknameChanger" style="display:none;">
                    <div>
                        <label for="inputNewNicname">새 닉네임 : </label>
                        <input type="text" name="newNick" id="inputNewNickname">
                        <%-- <label for="inputNewNicname">
                            <button id="checkNickname">중복 검사</button>
                        </label> --%>
                    </div>
                    <div>
                        <button id="modifyNicknameBtn">수정</button>
                    </div>
                    <br>
                </div>
            </div>
            <div>
                <input type="radio" id="pw" name="select" value="pw"/>
                <label for="pw">비밀번호 변경</label>
                <input type="radio" id="name" name="select" value="name"/>
                <label for="name">이름 변경</label>
                <input type="radio" id="nick" name="select" value="nick"/>
                <label for="nick">닉네임 변경</label>
            </div>
        </div>
    </div>
    <br>
    <div>
        <h3>회원 탈퇴</h3>
        <button id="deleteUserBtn">회원 탈퇴</button>
    </div>
</div>

<script type="text/javascript" src="/js/User/mypage.js" ></script>
<script type="text/javascript" src="/js/nav.js" ></script>

<%@include file = "../footer.jsp"%>