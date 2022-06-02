package com.sparta.assignment3_test.validator;

import com.sparta.assignment3_test.dto.UserRequestDto;
import com.sparta.assignment3_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public
class CheckUsernameValidator extends AbstractValidator<UserRequestDto> {
    private final UserRepository userRepository;
    @Override
    protected void doValidate(UserRequestDto dto, Errors errors) {
        if (userRepository.existsByUsername(dto.toEntity().getUsername())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
