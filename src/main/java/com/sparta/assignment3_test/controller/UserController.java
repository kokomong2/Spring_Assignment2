package com.sparta.assignment3_test.controller;


import com.sparta.assignment3_test.dto.SignupRequestDto;
import com.sparta.assignment3_test.exception.BadRequestException;
import com.sparta.assignment3_test.model.User;
import com.sparta.assignment3_test.repository.UserRepository;
import com.sparta.assignment3_test.security.UserDetailsImpl;
import com.sparta.assignment3_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import java.util.List;
import java.util.Map;

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
    @PostMapping("/user/signup") // Error errors 를 BindingResult bindingResult로 수정함
    public String registerUser(SignupRequestDto userDto) {
        try{
            userService.registerUser(userDto);
            return "redirect:/user/login";
        }catch (Exception e){
            return "signup";
        }
    }

    //전체 유저 조회
    @ResponseBody
    @GetMapping("/user/all")
    public List<User> getUsers(){
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