package com.cooper.demoatmapp.account.domain;

import com.cooper.demoatmapp.account.listener.AccountHistoryListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class, AccountHistoryListener.class})
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false, unique = true, updatable = false, length = 30)
    private String accountNumber;

    @Column(nullable = false, length = 30)
    private String password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "account_balance"))
    private Money balance;

    @Column(name = "account_user_id", nullable = false)
    private String userId;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    private Account(String accountNumber, String password, Money balance, String userId) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.userId = userId;
    }

    public static Account create(String accountNumber, String password, String userId) {
        return new Account(accountNumber, password, new Money(BigInteger.ZERO), userId);
    }

    public boolean matchesPassword(String password) {
        return this.password.equals(password);
    }

    public void depositMoney(Money depositMoney) {
        this.balance.addMoney(depositMoney);
    }

    public void withdrawMoney(Money withdrawMoney) {
        this.balance.subtractMoney(withdrawMoney);
    }

}
