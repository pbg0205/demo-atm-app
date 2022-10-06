package com.cooper.demoatmapp.account.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
public class AccountHistoryLookupDto {

    private final String id;
    private final BigInteger currentBalance;
    private final BigInteger currentMoney;
    private final LocalDateTime createdDate;

    @QueryProjection
    public AccountHistoryLookupDto(
            String id,
            BigInteger currentBalance,
            BigInteger currentMoney,
            LocalDateTime createdDate
    ) {
        this.id = id;
        this.currentBalance = currentBalance;
        this.currentMoney = currentMoney;
        this.createdDate = createdDate;
    }
}
