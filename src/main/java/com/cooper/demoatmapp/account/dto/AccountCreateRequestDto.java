package com.cooper.demoatmapp.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequestDto {

    private String name;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;

}
