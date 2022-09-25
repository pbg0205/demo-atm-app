package com.cooper.demoatmapp.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDepositRequestDto {

    @NotNull
    private String accountNumber;

    @NotNull
    private String password;

    @Positive
    private BigInteger depositMoney;

}
