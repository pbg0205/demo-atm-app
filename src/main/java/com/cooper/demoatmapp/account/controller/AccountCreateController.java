package com.cooper.demoatmapp.account.controller;

import com.cooper.demoatmapp.account.dto.AccountCreateRequestDto;
import com.cooper.demoatmapp.account.dto.AccountCreateResponseDto;
import com.cooper.demoatmapp.account.service.AccountCreateService;
import com.cooper.demoatmapp.common.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountCreateController {

    private final AccountCreateService accountCreateService;

    @PostMapping
    public ApiResult<AccountCreateResponseDto> createAccount(
            @RequestBody AccountCreateRequestDto accountCreateRequestDto
    ) {
        AccountCreateResponseDto accountCreateResponseDto = accountCreateService.createAccount(accountCreateRequestDto);
        return ApiResult.success(accountCreateResponseDto, HttpStatus.OK);
    }

}
