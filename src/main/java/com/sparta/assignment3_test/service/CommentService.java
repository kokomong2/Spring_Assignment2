package com.sparta.assignment3_test.service;

import com.sparta.assignment3_test.dto.CommentRequestDto;
import com.sparta.assignment3_test.dto.MemoRequestDto;
import com.sparta.assignment3_test.model.Comment;
import com.sparta.assignment3_test.model.Memo;
import com.sparta.assignment3_test.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new NullPointerException("아이디가 존재하지 않습니다")
        );
        comment.update(requestDto);
        return comment.getId();
    }
}
