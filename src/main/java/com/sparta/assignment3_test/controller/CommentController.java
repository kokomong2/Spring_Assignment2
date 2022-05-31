package com.sparta.assignment3_test.controller;

import com.sparta.assignment3_test.dto.CommentRequestDto;
import com.sparta.assignment3_test.model.Comment;
import com.sparta.assignment3_test.model.User;
import com.sparta.assignment3_test.repository.CommentRepository;
import com.sparta.assignment3_test.repository.UserRepository;
import com.sparta.assignment3_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @PostMapping("/api/memos/comments")
    public Comment postComments(@RequestBody CommentRequestDto requestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();
        Comment comment = new Comment(requestDto, userId);
        return commentRepository.save(comment);
    }

    @GetMapping("/comments/all")
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    @GetMapping("/api/comments/{id}")
    public List<Comment> getMemoComments(@PathVariable Long id){
        return commentRepository.findAllByMemoId(id);
    }

    @GetMapping("/api/comments/username/{id}")
    public String getCommentUsername(@PathVariable Long id){
        Long userId = commentRepository.findById(id).orElseThrow(()->new NullPointerException("아이디가 없습니다.")).getUserId();
        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("아이디가 없습니다."));

        return user.getUsername();
    }
}
