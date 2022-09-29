package com.cooper.demoatmapp.account.listener;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.domain.Money;
import com.cooper.demoatmapp.account.dto.AccountHistoryCreateDto;
import com.cooper.demoatmapp.account.dto.AccountHistoryLookupDto;
import com.cooper.demoatmapp.account.service.AccountHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Component
public class AccountHistoryListener {

    @Lazy
    @Autowired
    private AccountHistoryService accountHistoryService;

    @PostPersist
    public void postPersist(Account account) {
        AccountHistoryCreateDto accountHistoryCreateDto = AccountHistoryCreateDto.create(
                account.getAccountNumber(),
                account.getBalance().getValue(),
                account.getBalance().getValue(),
                LocalDateTime.now()
        );
        accountHistoryService.save(accountHistoryCreateDto);
    }

    @PostUpdate
    public void postUpdate(Account account) {
        AccountHistoryLookupDto currentAccountHistoryLookupDto
                = accountHistoryService.findTopByCreatedDateDesc(account.getAccountNumber());

        long currentBalanceValue = account.getBalance().getValue().longValue();
        Money currentBalance = Money.of(BigInteger.valueOf(currentBalanceValue));

        long currentMoneyValue = currentAccountHistoryLookupDto.getCurrentMoney().longValue();
        Money currentMoney = Money.of(BigInteger.valueOf(currentMoneyValue));

        Money newCurrentMoney = Money.of(BigInteger.valueOf(currentBalance.getValue().longValue()));
        Money newMoneyHistory
                = Money.of(currentBalance.getValue().subtract(currentAccountHistoryLookupDto.getCurrentBalance()));

        AccountHistoryCreateDto accountHistoryCreateDto = AccountHistoryCreateDto.create(
                account.getAccountNumber(),
                newCurrentMoney.getValue(),
                newMoneyHistory.getValue(),
                LocalDateTime.now()
        );

        accountHistoryService.save(accountHistoryCreateDto);
    }

}
