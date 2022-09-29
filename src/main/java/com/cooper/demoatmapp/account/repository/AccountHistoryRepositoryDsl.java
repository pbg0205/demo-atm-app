package com.cooper.demoatmapp.account.repository;

import com.cooper.demoatmapp.account.dto.AccountHistoryLookupDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.cooper.demoatmapp.account.domain.QAccountHistory.accountHistory;

@Repository
@RequiredArgsConstructor
public class AccountHistoryRepositoryDsl implements AccountHistoryRepository{

    private final JPAQueryFactory queryFactory;

    public AccountHistoryLookupDto findTopByCreatedDateDesc(String accountNumber) {
        return queryFactory.select(Projections.constructor(AccountHistoryLookupDto.class,
                        accountHistory.id,
                        accountHistory.currentBalance.value,
                        accountHistory.moneyHistory.value,
                        accountHistory.createdDate))
                .from(accountHistory)
                .where(accountHistory.accountNumber.eq(accountNumber))
                .orderBy(accountHistory.createdDate.desc())
                .limit(1)
                .fetchOne();
    }

}
