package com.cooper.demoatmapp.account.exception;

public class AccountPasswordNotMatchException extends RuntimeException {

    private static final String ACCOUNT_PASSWORD_NOT_MATCH_MESSAGE = "계좌 비밀번호가 일치하지 않습니다";

    public AccountPasswordNotMatchException() {
        super(ACCOUNT_PASSWORD_NOT_MATCH_MESSAGE);
    }

}
