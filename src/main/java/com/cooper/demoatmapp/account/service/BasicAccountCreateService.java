package com.cooper.demoatmapp.account.service;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.dto.AccountCreateResponseDto;
import com.cooper.demoatmapp.account.dto.AccountCreateRequestDto;
import com.cooper.demoatmapp.account.repository.AccountCommandRepository;
import com.cooper.demoatmapp.user.domain.User;
import com.cooper.demoatmapp.user.dto.UserRegisterRequestDto;
import com.cooper.demoatmapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BasicAccountCreateService implements AccountCreateService {

    private static final String ACCOUNT_PREFIX_NUMBER = "001";
    private static final String ACCOUNT_MIDDLE_NUMBER = "00";
    private static final int ACCOUNT_LAST_NUMBER_LENGTH = 9;

    private final AccountCommandRepository accountCommandRepository;
    private final UserService userService;

    @Override
    public AccountCreateResponseDto createAccount(AccountCreateRequestDto accountCreateRequestDto) {
        User user = userService.findUserByNameAndAndPhoneNumber(
                accountCreateRequestDto.getUserName(),
                accountCreateRequestDto.getPhoneNumber())
                .orElseGet(() -> registerUser(accountCreateRequestDto));

        Account account = Account.create(createAccountNumber(), accountCreateRequestDto.getPassword(), user.getId());
        Account createdAccount = accountCommandRepository.save(account);

        return AccountCreateResponseDto.builder()
                .userName(user.getName())
                .phoneName(user.getPhoneNumber())
                .accountNumber(createdAccount.getAccountNumber())
                .email(user.getEmail())
                .build();
    }

    private User registerUser(AccountCreateRequestDto accountCreateRequestDto) {
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .name(accountCreateRequestDto.getUserName())
                .email(accountCreateRequestDto.getEmail())
                .phoneNumber(accountCreateRequestDto.getPhoneNumber())
                .build();

        return userService.createUser(userRegisterRequestDto);
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
