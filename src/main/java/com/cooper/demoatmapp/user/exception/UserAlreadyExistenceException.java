package com.cooper.demoatmapp.user.exception;

public class UserAlreadyExistenceException extends RuntimeException {

    private static final String USER_EXIST_MESSAGE = "존재하는 회원입니다";

    public UserAlreadyExistenceException() {
        super(USER_EXIST_MESSAGE);
    }

}
