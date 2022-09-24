package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.domain.Money;
import com.cooper.demoatmapp.account.dto.AccountWithdrawalRequestDto;
import com.cooper.demoatmapp.account.dto.AccountWithdrawalResponseDto;
import com.cooper.demoatmapp.account.exception.AccountNotFoundException;
import com.cooper.demoatmapp.account.exception.AccountPasswordNotMatchException;
import com.cooper.demoatmapp.account.exception.InsufficientAccountBalanceException;
import com.cooper.demoatmapp.account.exception.NegativeMoneyException;
import com.cooper.demoatmapp.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class AccountWithdrawService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountWithdrawalResponseDto withdraw(AccountWithdrawalRequestDto accountWithdrawalRequestDto) {
        Account account = accountRepository.findAccountByAccountNumber(accountWithdrawalRequestDto.getAccountNumber())
                .orElseThrow(AccountNotFoundException::new);

        if(!account.matchesPassword(accountWithdrawalRequestDto.getPassword())) {
            throw new AccountPasswordNotMatchException();
        }

        withdraw(accountWithdrawalRequestDto, account);

        return AccountWithdrawalResponseDto.fromEntity(account);
    }

    private void withdraw(AccountWithdrawalRequestDto accountWithdrawalRequestDto, Account account) {
        try {
            Money withdrawalMoney = Money.of(accountWithdrawalRequestDto.getWithdrawalMoney());
            account.withdrawMoney(withdrawalMoney);
        } catch (NegativeMoneyException negativeMoneyException) {
            throw new InsufficientAccountBalanceException();
        }
    }

}
