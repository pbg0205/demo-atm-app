package com.cooper.demoatmapp.account.repository;

import com.cooper.demoatmapp.account.dto.AccountHistoryLookupDto;

public interface AccountHistoryRepository {

    AccountHistoryLookupDto findTopByCreatedDateDesc(String accountNumber);

}
