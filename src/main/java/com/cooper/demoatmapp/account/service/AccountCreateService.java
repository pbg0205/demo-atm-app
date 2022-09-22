package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.dto.AccountCreateRequestDto;
import com.cooper.demoatmapp.account.dto.AccountCreateResponseDto;

public interface AccountCreateService {

    AccountCreateResponseDto createAccount(AccountCreateRequestDto accountCreateRequestDto);

}
