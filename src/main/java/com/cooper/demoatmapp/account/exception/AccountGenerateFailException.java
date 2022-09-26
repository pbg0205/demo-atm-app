package com.cooper.demoatmapp.account.exception;

public class AccountGenerateFailException extends RuntimeException {

    private static final String ACCOUNT_GENERATE_FAIL_MESSAGE = "계좌 생성에 실패했습니다. 다시 시도해주세요.(시도횟수 : %d)";

    public AccountGenerateFailException(int generateCount) {
        super(String.format(ACCOUNT_GENERATE_FAIL_MESSAGE, generateCount));
    }

}
