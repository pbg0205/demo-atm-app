package com.cooper.demoatmapp.account.repository;

import com.cooper.demoatmapp.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountCommandRepository extends JpaRepository<Account, String> {
}
