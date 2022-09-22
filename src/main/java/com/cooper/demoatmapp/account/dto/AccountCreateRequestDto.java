package com.cooper.demoatmapp.account.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountCreateRequestDto {

    private final String userName;
    private final String phoneNumber;
    private final String email;
    private final String password;

}
