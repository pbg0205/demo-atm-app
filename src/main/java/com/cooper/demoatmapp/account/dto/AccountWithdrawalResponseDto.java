package com.cooper.demoatmapp.account.dto;

import com.cooper.demoatmapp.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountWithdrawalResponseDto {

    private String accountNumber;
    private BigInteger balance;

    public static AccountWithdrawalResponseDto fromEntity(Account account) {
        return new AccountWithdrawalResponseDto(account.getAccountNumber(), account.getBalance().getValue());
    }

}
