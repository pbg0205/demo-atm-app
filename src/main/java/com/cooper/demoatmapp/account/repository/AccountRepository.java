package com.cooper.demoatmapp.account.repository;

import com.cooper.demoatmapp.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);

}
