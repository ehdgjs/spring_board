package com.project.board.web.User.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ModifyUserDto {
    
    // 회원 ID
    private String USER_ID;

    // 변경할 PW
    private String PASSWORD;

    // 변경할 Name
    private String NAME;

    // 변경할 Nickname
    private String NICKNAME;

}
