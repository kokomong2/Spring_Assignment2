package com.sparta.assignment3_test.controller;


import com.sparta.assignment3_test.dto.SignupRequestDto;
import com.sparta.assignment3_test.model.User;
import com.sparta.assignment3_test.repository.UserRepository;
import com.sparta.assignment3_test.security.UserDetailsImpl;
import com.sparta.assignment3_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }
    //전체 유저 조회
    @ResponseBody
    @GetMapping("/user/all")
    public List<User> getUsers(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userRepository.findAll();
    }

    @PostMapping("/user/userinfo")
    @ResponseBody
    public String getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username="";
        if (userDetails != null) {
            username= userDetails.getUser().getUsername();
        }
        return username;
    }
}