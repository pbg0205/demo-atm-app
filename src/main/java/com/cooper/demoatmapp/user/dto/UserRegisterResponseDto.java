package com.cooper.demoatmapp.user.dto;

import com.cooper.demoatmapp.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterResponseDto {

    private String userId;

    public static UserRegisterResponseDto fromEntity(User user) {
        return new UserRegisterResponseDto(user.getId());
    }
}
