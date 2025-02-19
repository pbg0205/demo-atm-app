package com.cooper.demoatmapp.account.domain;

import com.cooper.demoatmapp.account.converter.AccountAttributeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@EntityListeners(value = AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false, unique = true, updatable = false, length = 30)
    @Convert(converter = AccountAttributeConverter.class)
    private String accountNumber;

    @Column(nullable = false, length = 30)
    private String password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "account_balance"))
    private Money balance;

    @Column(name = "account_user_id", nullable = false)
    private String userId;

    @CreatedDate
    @Column(name = "created_date")
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
