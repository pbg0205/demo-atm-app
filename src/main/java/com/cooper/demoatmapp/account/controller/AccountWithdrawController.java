package com.cooper.demoatmapp.account.controller;

import com.cooper.demoatmapp.account.dto.AccountWithdrawalRequestDto;
import com.cooper.demoatmapp.account.dto.AccountWithdrawalResponseDto;
import com.cooper.demoatmapp.account.service.AccountWithdrawService;
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
public class AccountWithdrawController {

    private final AccountWithdrawService accountWithdrawalService;

    @PutMapping("/withdrawal")
    public ApiResult<AccountWithdrawalResponseDto> withdrawFromAccount(
            @RequestBody @Valid AccountWithdrawalRequestDto accountWithdrawalRequestDto
    ) {
        AccountWithdrawalResponseDto accountDepositResponseDto
                = accountWithdrawalService.withdraw(accountWithdrawalRequestDto);
        return ApiResult.success(accountDepositResponseDto, HttpStatus.OK);
    }

}
