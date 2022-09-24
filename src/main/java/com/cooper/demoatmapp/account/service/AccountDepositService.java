package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.domain.Money;
import com.cooper.demoatmapp.account.dto.AccountDepositRequestDto;
import com.cooper.demoatmapp.account.dto.AccountDepositResponseDto;
import com.cooper.demoatmapp.account.exception.AccountNotFoundException;
import com.cooper.demoatmapp.account.exception.AccountPasswordNotMatchException;
import com.cooper.demoatmapp.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountDepositService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountDepositResponseDto deposit(AccountDepositRequestDto accountDepositRequestDto) {
        Account account = accountRepository.findAccountByAccountNumber(accountDepositRequestDto.getAccountNumber())
                .orElseThrow(AccountNotFoundException::new);

        if(!account.matchesPassword(accountDepositRequestDto.getPassword())) {
            throw new AccountPasswordNotMatchException();
        }

        Money depositMoney = Money.of(accountDepositRequestDto.getDepositMoney());
        account.depositMoney(depositMoney);

        return AccountDepositResponseDto.fromEntity(account);
    }

}
