package com.cooper.demoatmapp.account.controller;

import com.cooper.demoatmapp.account.dto.AccountDepositRequestDto;
import com.cooper.demoatmapp.account.dto.AccountDepositResponseDto;
import com.cooper.demoatmapp.account.service.AccountDepositService;
import com.cooper.demoatmapp.common.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountDepositController {

    private final AccountDepositService accountDepositService;

    @PutMapping("/deposit")
    public ApiResult<AccountDepositResponseDto> deposit(
            @RequestBody @Valid AccountDepositRequestDto accountDepositRequestDto
    ) {
        AccountDepositResponseDto accountDepositResponseDto = accountDepositService.deposit(accountDepositRequestDto);
        return ApiResult.success(accountDepositResponseDto, HttpStatus.OK);
    }

}
