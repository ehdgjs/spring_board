package com.project.board.web.User.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserSignUpDto {

    // 회원 아이디
    private String USER_ID;

    // 회원 비밀번호
    private String PASSWORD;

    // 회원 이름
    private String NAME;

    // 회원 닉네임
    private String NICKNAME;

}
