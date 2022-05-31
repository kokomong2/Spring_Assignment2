package com.sparta.assignment3_test.repository;

import com.sparta.assignment3_test.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start,LocalDateTime end);
    List<Memo> findAllByOrderByModifiedAtDesc();
    List<Memo> findAllById(Long userId);
}
