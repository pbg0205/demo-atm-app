package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.dto.AccountCreateResponseDto;
import com.cooper.demoatmapp.account.dto.AccountCreateRequestDto;
import com.cooper.demoatmapp.account.repository.AccountRepository;
import com.cooper.demoatmapp.user.dto.UserLookupResponseDto;
import com.cooper.demoatmapp.user.dto.UserRegisterRequestDto;
import com.cooper.demoatmapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BasicAccountCreateService implements AccountCreateService {

    private static final String ACCOUNT_PREFIX_NUMBER = "001";
    private static final String ACCOUNT_MIDDLE_NUMBER = "00";
    private static final int ACCOUNT_LAST_NUMBER_LENGTH = 9;

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    @Transactional
    public AccountCreateResponseDto createAccount(AccountCreateRequestDto accountCreateRequestDto) {
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .name(accountCreateRequestDto.getName())
                .username(accountCreateRequestDto.getUsername())
                .phoneNumber(accountCreateRequestDto.getPhoneNumber())
                .email(accountCreateRequestDto.getEmail())
                .build();

        if (!userService.existsByUsername(userRegisterRequestDto.getUsername())) {
            userService.registerUser(userRegisterRequestDto);
        }

        UserLookupResponseDto userLookupResponseDto = userService.findByUsername(userRegisterRequestDto.getUsername());

        Account account = Account.create(
                createAccountNumber(),
                accountCreateRequestDto.getPassword(),
                userLookupResponseDto.getUserId()
        );
        Account createdAccount = accountRepository.save(account);

        return AccountCreateResponseDto.builder()
                .name(userLookupResponseDto.getName())
                .phoneName(userLookupResponseDto.getPhoneNumber())
                .accountNumber(createdAccount.getAccountNumber())
                .email(userLookupResponseDto.getEmail())
                .build();
    }

    private String createAccountNumber() {
        StringBuilder lastNumber = new StringBuilder();
        Random randomNumber = new Random();

        IntStream.range(1, ACCOUNT_LAST_NUMBER_LENGTH)
                .forEach(number -> lastNumber.append(randomNumber.nextInt(10)));

        List<String> accountNumbers = List.of(ACCOUNT_PREFIX_NUMBER, ACCOUNT_MIDDLE_NUMBER, lastNumber.toString());
        return String.join("-", accountNumbers);
    }

}
