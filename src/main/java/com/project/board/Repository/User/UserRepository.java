package com.project.board.Repository.User;

import com.project.board.web.User.dto.ModifyUserDto;
import com.project.board.web.User.dto.UserDto;
import com.project.board.web.User.dto.UserLoginDto;
import com.project.board.web.User.dto.UserSignUpDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    
    // ID를 이용해 회원 정보 검색
    UserDto findByUserId(UserLoginDto userLoginDto);
    
    // 회원가입
    void signUpUser(UserSignUpDto userSignUpDto);

    // 아이디 중복 체크
    UserDto checkValidationId(String id);

    // 닉네임 중복 체크
    UserDto checkValidationNickName(String nickname);

    // 비밀번호 변경
    void modifyPassword(ModifyUserDto modifyUserDto);

    // 이름 변경
    void modifyName(ModifyUserDto modifyUserDto);

    // 닉네임 변경
    void modifyNickname(ModifyUserDto modifyUserDto);
    
    // 회원 탈퇴
    void deleteUser(String id);

}
