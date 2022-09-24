package com.cooper.demoatmapp.account.domain;

import com.cooper.demoatmapp.account.exception.NegativeMoneyException;
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

    public static Money of(BigInteger value) {
        return new Money(value);
    }

    public void addMoney(Money depositMoney) {
        this.value = this.value.add(depositMoney.value);
    }

    public void subtractMoney(Money withdrawMoney) {
        BigInteger nowValue = BigInteger.valueOf(value.longValue());
        BigInteger subtractValue = nowValue.subtract(withdrawMoney.value);

        if(subtractValue.longValue() < 0) {
            throw new NegativeMoneyException(subtractValue.longValue());
        }

        this.value = this.value.subtract(withdrawMoney.value);
    }

}
