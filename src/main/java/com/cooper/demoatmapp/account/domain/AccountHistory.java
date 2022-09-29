package com.cooper.demoatmapp.account.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "account_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AccountHistory {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "account_number", nullable = false, updatable = false)
    private String accountNumber;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "current_money"))
    private Money currentBalance;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "money_history"))
    private Money moneyHistory;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    private AccountHistory(String accountNumber, Money currentBalance, Money moneyHistory, LocalDateTime createdDate) {
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.moneyHistory = moneyHistory;
        this.createdDate = createdDate;
    }

    public static AccountHistory create(
            String accountNumber,
            Money currentMoney,
            Money moneyHistory,
            LocalDateTime createdDate
    ) {
        return new AccountHistory(accountNumber, currentMoney, moneyHistory, createdDate);
    }

}
