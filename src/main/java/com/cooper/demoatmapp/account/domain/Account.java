package com.cooper.demoatmapp.account.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "account_id")
    private String id;

    @Column(name = "account_number", nullable = false, unique = true, updatable = false, length = 30)
    private String accountNumber;

    @Column(name = "account_password", nullable = false, unique = true, length = 30)
    private String password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "account_balance"))
    private Money balance;

    @Column(name = "account_user_id", nullable = false)
    private String userId;

    private Account(String accountNumber, String password, Money balance, String userId) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.userId = userId;
    }

    public static Account create(String accountNumber, String password, Money money, String userId) {
        return new Account(accountNumber, password, money, userId);
    }

}
