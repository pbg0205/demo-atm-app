package com.cooper.demoatmapp.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigInteger;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Money {

    private BigInteger value;

    public Money addMoney(Money depositMoney) {
        BigInteger addedMoney = this.value.add(depositMoney.getValue());
        return new Money(addedMoney);
    }

}
