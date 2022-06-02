package com.sparta.assignment3_test.repository;


import com.sparta.assignment3_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userid);

    boolean existsByUsername(String username);
}