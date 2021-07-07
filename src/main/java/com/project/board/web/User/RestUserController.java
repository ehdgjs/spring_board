package com.project.board.web.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.project.board.service.User.UserService;
import com.project.board.session.UserInfo;
import com.project.board.util.ApiResponse;
import com.project.board.web.User.dto.ModifyUserDto;
import com.project.board.web.User.dto.UserLoginDto;
import com.project.board.web.User.dto.UserSignUpDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class RestUserController {

    private final UserService userService;

    private final UserInfo userInfo;
    private Logger logger = LoggerFactory.getLogger(RestUserController.class);

    // 로그인
    @PostMapping(value="/login")
    public ResponseEntity<?> login(UserLoginDto userLoginDto) {
        ApiResponse result = null;
        try {
            if (userService.login(userLoginDto)) {
                result = new ApiResponse(true, "성공", null);

                userInfo.setUserId(userLoginDto.getUSER_ID());

                return ResponseEntity.ok().body(result);
            } else {
                result = new ApiResponse(false, "비밀번호가 틀렸습니다.", null);
                
                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            
            result = new ApiResponse(false, "ID가 존재하지 않습니다.", userLoginDto);

            return ResponseEntity.ok().body(result);
        }
    }
    
    // 회원가입
    @PostMapping(value="/signUp")
    public ResponseEntity<?> signUp(UserSignUpDto userSignUpDto) {
        ApiResponse result = null;
        try {

            if (userService.signUp(userSignUpDto) == 1) {
                result = new ApiResponse(true, "회원가입이 완료되었습니다.", 1);

                return ResponseEntity.ok().body(result);

            } else if (userService.signUp(userSignUpDto) == 2) {
                result = new ApiResponse(true, "중복된 아이디가 존재합니다.", 2);

                return ResponseEntity.ok().body(result);

            } else {
                result = new ApiResponse(true, "중복된 닉네임이 존재합니다.", 3);

                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            logger.error("/signUp => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), userSignUpDto);

            return ResponseEntity.badRequest().body(result);
        }        
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ApiResponse result = null;

        userInfo.setUserId("");

        result = new ApiResponse(true, "성공", userInfo.getUserId());
        
        return ResponseEntity.ok().body(result);
    }

    // ID 중복 체크
    // @GetMapping(value="/checkId")
    // public ResponseEntity<?> checkValidationId(@RequestParam(value = "id") String id) {
    //     ApiResponse result = null;

    //     try {

    //         if (userService.checkValidationId(id)) {

    //             result = new ApiResponse(true, "성공", null);

    //             return ResponseEntity.ok().body(result);

    //         } else {

    //             result = new ApiResponse(false, "중복된 아이디가 존재합니다.", null);

    //             return ResponseEntity.ok().body(result);

    //         }
            
    //     } catch (Exception e) {
    //         logger.error("/checkId => " + e.getMessage());

    //         result = new ApiResponse(false, e.getMessage(), id);

    //         return ResponseEntity.badRequest().body(result);
    //     }
        
    // }
    

    // NickName 중복 체크
    // @GetMapping(value = "/checkNickname")
    // public ResponseEntity<?> checkValidationNickname(@RequestParam(value = "nickname") String nickname) {
    //     ApiResponse result = null;

    //     try {
    //         if (userService.checkValidationNickname(nickname)) {
                
    //             result = new ApiResponse(true, "사용가능한 닉네임입니다.", null);

    //             return ResponseEntity.ok().body(result);

    //         } else {

    //             result = new ApiResponse(false, "중복된 닉네임이 존재합니다.", null);

    //             return ResponseEntity.ok().body(result);

    //         }
    //     } catch (Exception e) {
    //         logger.error("/checkNickname => " + e.getMessage());

    //         result = new ApiResponse(false, e.getMessage(), nickname);

    //         return ResponseEntity.badRequest().body(result);
    //     }
    // }

    // 회원 정보 수정
    @PostMapping("/modifyUserInfo")
    public ResponseEntity<?> modifyUserInfo(ModifyUserDto modifyUserDto) {
        ApiResponse result = null;

        modifyUserDto.setUSER_ID(userInfo.getUserId());

        try {
            if(userService.modifyUserInfo(modifyUserDto)) {
                result = new ApiResponse(true, "성공", null);

                return ResponseEntity.ok().body(result);
            } else {
                result = new ApiResponse(false, "중복된 닉네임이 존재합니다.", null);

                return ResponseEntity.ok().body(result);
            }
        } catch (Exception e) {
            logger.error("/modifyUserInfo => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), modifyUserDto);

            return ResponseEntity.badRequest().body(result);
        }
    }

    // 회원 탈퇴
    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser() {
        ApiResponse result = null;

        try {
            userService.deleteUser(userInfo.getUserId());

            result = new ApiResponse(true, "회원 탈퇴가 완료되었습니다.", null);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            logger.error("/checkNickname => " + e.getMessage());

            result = new ApiResponse(false, e.getMessage(), null);

            return ResponseEntity.badRequest().body(result);
        }
    }
}
