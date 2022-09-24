package com.cooper.demoatmapp.user.dto;

import com.cooper.demoatmapp.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterRequestDto {

    private final String name;
    private final String username;
    private final String phoneNumber;
    private final String email;

    public User toEntity() {
        return User.create(name, username, phoneNumber, email);
    }

}
