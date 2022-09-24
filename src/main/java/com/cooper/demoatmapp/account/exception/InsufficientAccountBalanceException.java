package com.cooper.demoatmapp.account.exception;

public class InsufficientAccountBalanceException extends RuntimeException {

    private static final String INSUFFICIENT_ACCOUNT_BALANCE_MESSAGE = "작고가 부족합니다";

    public InsufficientAccountBalanceException() {
        super(INSUFFICIENT_ACCOUNT_BALANCE_MESSAGE);
    }

}
