package com.project.board.web.User;

import com.project.board.session.UserInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final UserInfo userInfo;

    @GetMapping("/login.do")
    public String getLogin() {
        return "User/login";
    }

    @GetMapping(value="/signUp.do")
    public String getSignUp() {
        return "User/signUp";
    }

    @GetMapping(value="/mypage.do")
    public String getMypage(Model model) {

        model.addAttribute("userInfo", userInfo.getUserId());
        
        return "User/mypage";
    }
    
    

}
