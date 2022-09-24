package com.cooper.demoatmapp.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateResponseDto {

    private String name;
    private String phoneName;
    private String email;
    private String accountNumber;

}
