package com.cooper.demoatmapp.account.dto;

import com.cooper.demoatmapp.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDepositResponseDto {

    private String accountNumber;
    private BigInteger balance;

    public static AccountDepositResponseDto fromEntity(Account account) {
        return new AccountDepositResponseDto(account.getAccountNumber(), account.getBalance().getValue());
    }

}
