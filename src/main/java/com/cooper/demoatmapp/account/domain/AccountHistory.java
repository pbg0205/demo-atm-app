package com.cooper.demoatmapp.account.domain;

import com.cooper.demoatmapp.account.converter.AccountAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
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
    @Convert(converter = AccountAttributeConverter.class)
    private String accountNumber;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "current_money"))
    private Money currentBalance;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "money_history"))
    private Money moneyHistory;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    private AccountHistory(String accountNumber, Money currentBalance, Money moneyHistory) {
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.moneyHistory = moneyHistory;
    }

    public static AccountHistory create(String accountNumber, Money currentMoney, Money moneyHistory) {
        return new AccountHistory(accountNumber, currentMoney, moneyHistory);
    }

}
