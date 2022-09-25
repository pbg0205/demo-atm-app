package com.cooper.demoatmapp.user.exception.handler;

import com.cooper.demoatmapp.common.ApiResult;
import com.cooper.demoatmapp.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ApiResult<String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ApiResult.fail(HttpStatus.BAD_REQUEST, userNotFoundException.getMessage());
    }

}
