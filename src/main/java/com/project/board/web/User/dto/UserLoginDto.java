package com.project.board.web.User.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserLoginDto {

    // 회원 아이디
    private String USER_ID;

    // 회원 비밀번호
    private String PASSWORD;
        
}
