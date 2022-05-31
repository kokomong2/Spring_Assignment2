package com.sparta.assignment3_test.controller;


import com.sparta.assignment3_test.security.UserDetailsImpl;
import com.sparta.assignment3_test.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemoService memoService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userId", userDetails.getUser().getId());
        return "index";
    }
    @GetMapping("/memos/commentPage")
    public String getBoardContent(Model model, @RequestParam("memoid") Long memoid) throws Exception {
        model.addAttribute("commentsMemo", memoService.getMemos(memoid));
        return "commentPage";
    }
}