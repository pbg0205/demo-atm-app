package com.cooper.demoatmapp.user.exception;

public class UserNotExistException extends RuntimeException {

    private static final String USER_NOT_EXIST_MESSAGE = "존재하지 않는 회원입니다";

    public UserNotExistException() {
        super(USER_NOT_EXIST_MESSAGE);
    }

}
