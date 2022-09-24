package com.cooper.demoatmapp.account.exception;

public class NegativeMoneyException extends RuntimeException {

    private static final String NEGATIVE_MONEY_MESSAGE_FORMAT = "돈은 음수일 수 없습니다 : %f";

    public NegativeMoneyException(double value) {
        super(String.format(NEGATIVE_MONEY_MESSAGE_FORMAT, value));
    }

}
