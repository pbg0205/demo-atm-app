package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.dto.AccountDepositRequestDto;
import com.cooper.demoatmapp.account.dto.AccountDepositResponseDto;
import com.cooper.demoatmapp.account.exception.AccountNotFoundException;
import com.cooper.demoatmapp.account.exception.AccountPasswordNotMatchException;
import com.cooper.demoatmapp.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDepositService {

    private final AccountRepository accountRepository;

    public AccountDepositResponseDto deposit(AccountDepositRequestDto accountDepositRequestDto) {
        Account account = accountRepository.findAccountByAccountNumber(accountDepositRequestDto.getAccountNumber())
                .orElseThrow(AccountNotFoundException::new);

        if(!account.matchesPassword(accountDepositRequestDto.getPassword())) {
            throw new AccountPasswordNotMatchException();
        }

        account.depositMoney(accountDepositRequestDto.getDepositMoney());

        return AccountDepositResponseDto.fromEntity(account);
    }

}
