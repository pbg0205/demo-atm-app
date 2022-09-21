package com.cooper.demoatmapp.account.converter;

import com.cooper.demoatmapp.account.domain.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigInteger;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, BigInteger> {

    @Override
    public BigInteger convertToDatabaseColumn(Money money) {
        return money == null ? null : money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(BigInteger value) {
        return value == null ? null : new Money(value);
    }

}
