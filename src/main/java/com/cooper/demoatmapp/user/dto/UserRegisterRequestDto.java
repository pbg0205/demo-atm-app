package com.cooper.demoatmapp.user.dto;

import com.cooper.demoatmapp.user.domain.User;
import lombok.Builder;

@Builder
public class UserRegisterRequestDto {

    private final String name;
    private final String phoneNumber;
    private final String email;

    public User toEntity() {
        return User.create(name, phoneNumber, email);
    }
}
