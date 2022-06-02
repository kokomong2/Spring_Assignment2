package com.sparta.assignment3_test.repository;

import com.sparta.assignment3_test.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByMemoId(Long memoId);
    List<Comment> findAllByMemoIdOrderByModifiedAtDesc(Long memoId);
}
