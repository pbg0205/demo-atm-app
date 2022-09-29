package com.cooper.demoatmapp.account.repository;

import com.cooper.demoatmapp.account.domain.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepositoryJpa extends JpaRepository<AccountHistory, String> {
}
