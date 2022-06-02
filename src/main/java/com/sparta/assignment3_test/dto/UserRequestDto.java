package com.sparta.assignment3_test.dto;

import com.sparta.assignment3_test.controller.MemoController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;

    // 먼가 에러가 뜨면 여기가 문제일수도
    public UserDetails toEntity() {
        return User.builder()
                .username(username)
                .build();
    }
}
