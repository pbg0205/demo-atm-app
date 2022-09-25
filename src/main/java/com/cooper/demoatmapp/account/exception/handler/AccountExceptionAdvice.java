package com.cooper.demoatmapp.account.exception.handler;

import com.cooper.demoatmapp.account.exception.AccountNotFoundException;
import com.cooper.demoatmapp.account.exception.AccountPasswordNotMatchException;
import com.cooper.demoatmapp.common.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountPasswordNotMatchException.class)
    public ApiResult<String> handleAccountPasswordNotMatchException(
            AccountPasswordNotMatchException accountPasswordNotMatchException
    ) {
        return ApiResult.fail(HttpStatus.BAD_REQUEST, accountPasswordNotMatchException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountNotFoundException.class)
    public ApiResult<String> handleAccountNotFoundException(AccountNotFoundException accountNotFoundException) {
        return ApiResult.fail(HttpStatus.BAD_REQUEST, accountNotFoundException.getMessage());
    }

}
