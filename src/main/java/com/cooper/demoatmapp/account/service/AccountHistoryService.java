package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.domain.AccountHistory;
import com.cooper.demoatmapp.account.dto.AccountHistoryCreateRequestDto;
import com.cooper.demoatmapp.account.dto.AccountHistoryLookupDto;
import com.cooper.demoatmapp.account.repository.AccountHistoryRepository;
import com.cooper.demoatmapp.account.repository.AccountHistoryRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;

    private final AccountHistoryRepositoryJpa accountHistoryRepositoryJpa;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AccountHistoryLookupDto findTopByCreatedDateDesc(String accountNumber) {
        return accountHistoryRepository.findTopByCreatedDateDesc(accountNumber);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(AccountHistoryCreateRequestDto accountHistoryCreateRequestDto) {
        AccountHistory accountHistory = accountHistoryCreateRequestDto.toEntity();
        accountHistoryRepositoryJpa.save(accountHistory);
    }

}
