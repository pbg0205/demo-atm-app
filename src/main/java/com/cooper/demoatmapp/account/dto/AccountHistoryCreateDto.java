package com.cooper.demoatmapp.account.dto;

import com.cooper.demoatmapp.account.domain.AccountHistory;
import com.cooper.demoatmapp.account.domain.Money;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountHistoryCreateDto {

    private final String accountNumber;
    private final BigInteger balance;
    private final BigInteger moneyHistory;
    private final LocalDateTime createdDate;

    public static AccountHistoryCreateDto create(
            String accountNumber,
            BigInteger balance,
            BigInteger moneyHistory,
            LocalDateTime createdDate
    ) {
        return new AccountHistoryCreateDto(accountNumber, balance, moneyHistory, createdDate);
    }

    public AccountHistory toEntity() {
        return AccountHistory.create(accountNumber, Money.of(balance), Money.of(moneyHistory), createdDate);
    }

}
