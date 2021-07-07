package com.project.board.service.User;

import com.project.board.Repository.User.UserRepository;
import com.project.board.util.PasswordEncoding;
import com.project.board.web.User.dto.ModifyUserDto;
import com.project.board.web.User.dto.UserDto;
import com.project.board.web.User.dto.UserLoginDto;
import com.project.board.web.User.dto.UserSignUpDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    PasswordEncoding passwordEncoding = new PasswordEncoding();
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    // 로그인
    @Transactional(readOnly = true)
    public Boolean login(UserLoginDto userLoginDto) {
        UserDto userDto = this.findByUserId(userLoginDto);
        
        
        return passwordEncoding.matches(userLoginDto.getPASSWORD(), userDto.getPASSWORD());
    }

    // ID를 이용해 회원 정보 검색
    @Transactional(readOnly = true)
    public UserDto findByUserId(UserLoginDto userLoginDto) {
        return userRepository.findByUserId(userLoginDto);
    }

    // 회원가입
    @Transactional
    public int signUp(UserSignUpDto userSignUpDto) {

        if (!checkValidationId(userSignUpDto.getUSER_ID())) {
            return 2;
        }

        if (!checkValidationNickname(userSignUpDto.getNICKNAME())) {
            return 3;
        }

        // password 암호화
        String pw = passwordEncoding.encode(userSignUpDto.getPASSWORD());

        userSignUpDto.setPASSWORD(pw);

        userRepository.signUpUser(userSignUpDto);

        return 1;

    }

    // ID 중복 체크
    @Transactional(readOnly = true)
    public Boolean checkValidationId(String id) {
        if (userRepository.checkValidationId(id) == null) {
            // 중복 아이디가 없을 경우
            return true;

        } else {
            // 중복 아이디가 있을 경우
            return false;

        }
    }

    // 닉네임 중복 체크
    @Transactional(readOnly = true)
    public Boolean checkValidationNickname(String nickname) {
        if (userRepository.checkValidationNickName(nickname) == null) {
            // 중복 닉네임이 없을 경우
            return true;

        } else {
            // 중복 닉네임이 있을 경우
            return false;

        }
    }

    // 회원 정보 변경
    @Transactional
    public Boolean modifyUserInfo(ModifyUserDto modifyUserDto) {
        if (modifyUserDto.getPASSWORD() != null) {

            // password 암호화
            String pw = passwordEncoding.encode(modifyUserDto.getPASSWORD());

            modifyUserDto.setPASSWORD(pw);

            userRepository.modifyPassword(modifyUserDto);

            return true;
        } 
        if (modifyUserDto.getNAME() != null) {
            
            userRepository.modifyName(modifyUserDto);

            return true;
        }
        if (modifyUserDto.getNICKNAME() != null) {
            
            if (checkValidationNickname(modifyUserDto.getNICKNAME())) {
                
                userRepository.modifyNickname(modifyUserDto);

                return true;
            } else {

                return false;

            }

        }
        return false;
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(String id) {

        userRepository.deleteUser(id);

    }

    
}
