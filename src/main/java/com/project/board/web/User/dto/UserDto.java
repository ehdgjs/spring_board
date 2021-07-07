package com.project.board.web.User.dto;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {

    // 회원 아이디
    private String USER_ID;

    // 회원 비밀번호
    private String PASSWORD;

    // 회원 이름
    private String NAME;

    // 회원 닉네임
    private String NICKNAME;

    // 회원 가입 일자
    private Date CREATE_TIME;

    // 회원 정보 수정 일자
    private Date MODIFY_TIME;

}
