package com.cooper.demoatmapp.account.dto;

import com.cooper.demoatmapp.account.domain.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountWithdrawalRequestDto {

    private String accountNumber;
    private String password;
    private Money withdrawalMoney;

}
