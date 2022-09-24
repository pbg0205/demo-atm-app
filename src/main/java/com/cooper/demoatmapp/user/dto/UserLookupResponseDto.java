package com.cooper.demoatmapp.user.dto;

import com.cooper.demoatmapp.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLookupResponseDto {

    private String userId;
    private String name;
    private String phoneNumber;
    private String email;

    public static UserLookupResponseDto fromEntity(User user) {
        return new UserLookupResponseDto(user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail());
    }

}
