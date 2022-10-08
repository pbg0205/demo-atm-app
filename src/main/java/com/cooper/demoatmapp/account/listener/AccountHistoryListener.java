package com.cooper.demoatmapp.account.listener;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.domain.Money;
import com.cooper.demoatmapp.account.dto.AccountHistoryCreateRequestDto;
import com.cooper.demoatmapp.account.dto.AccountHistoryLookupDto;
import com.cooper.demoatmapp.account.service.AccountHistoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class AccountHistoryListener implements PostInsertEventListener, PostUpdateEventListener {

    private final AccountHistoryService accountHistoryService;

    public void saveAccountCreateHistory(Account account) {
        AccountHistoryCreateRequestDto accountHistoryCreateRequestDto = AccountHistoryCreateRequestDto.create(
                account.getAccountNumber(),
                account.getBalance().getValue(),
                account.getBalance().getValue()
        );
        accountHistoryService.save(accountHistoryCreateRequestDto);
    }

    public void saveAccountUpdateHistory(Account account) {
        AccountHistoryLookupDto currentAccountHistoryLookupDto
                = accountHistoryService.findTopByCreatedDateDesc(account.getAccountNumber());

        long currentBalanceValue = account.getBalance().getValue().longValue();
        Money currentBalance = Money.of(BigInteger.valueOf(currentBalanceValue));

        Money newCurrentMoney = Money.of(BigInteger.valueOf(currentBalance.getValue().longValue()));
        Money newMoneyHistory
                = Money.of(currentBalance.getValue().subtract(currentAccountHistoryLookupDto.getCurrentBalance()));

        AccountHistoryCreateRequestDto accountHistoryCreateRequestDto = AccountHistoryCreateRequestDto.create(
                account.getAccountNumber(),
                newCurrentMoney.getValue(),
                newMoneyHistory.getValue()
        );

        accountHistoryService.save(accountHistoryCreateRequestDto);
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        Object entity = event.getEntity();
        boolean accountType = entity instanceof Account;

        if (!accountType) {
            return;
        }

        EventSource eventSession = event.getSession();
        eventSession.getSession().getActionQueue().registerProcess(((success, session) -> {
            if(success) {
                saveAccountCreateHistory((Account) entity);
            }
        }));

    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        Object entity = event.getEntity();
        boolean accountType = entity instanceof Account;

        if (!accountType) {
            return;
        }

        EventSource eventSession = event.getSession();
        eventSession.getSession().getActionQueue().registerProcess(((success, session) -> {
            if(success) {
                saveAccountUpdateHistory((Account) entity);
            }
        }));
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return true;
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return PostInsertEventListener.super.requiresPostCommitHandling(persister);
    }

}
