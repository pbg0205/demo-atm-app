package com.cooper.demoatmapp.account.dto;

import com.cooper.demoatmapp.account.domain.AccountHistory;
import com.cooper.demoatmapp.account.domain.Money;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountHistoryCreateRequestDto {

    private final String accountNumber;
    private final BigInteger balance;
    private final BigInteger moneyHistory;

    public static AccountHistoryCreateRequestDto create(String accountNumber, BigInteger balance, BigInteger moneyHistory) {
        return new AccountHistoryCreateRequestDto(accountNumber, balance, moneyHistory);
    }

    public AccountHistory toEntity() {
        return AccountHistory.create(accountNumber, Money.of(balance), Money.of(moneyHistory));
    }

}
