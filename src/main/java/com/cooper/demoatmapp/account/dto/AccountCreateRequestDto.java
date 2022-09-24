package com.cooper.demoatmapp.account.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountCreateRequestDto {

    private final String name;
    private final String username;
    private final String phoneNumber;
    private final String email;
    private final String password;

}
