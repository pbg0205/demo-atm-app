package com.cooper.demoatmapp.account.exception;

public class AccountNotFoundException extends RuntimeException {

    private static final String ACCOUNT_NOT_FOUND_MESSAGE = "존재하지 않는 계좌입니다";

    public AccountNotFoundException() {
        super(ACCOUNT_NOT_FOUND_MESSAGE);
    }

}
