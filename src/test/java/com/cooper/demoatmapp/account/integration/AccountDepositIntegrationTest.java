package com.cooper.demoatmapp.account.integration;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.dto.AccountDepositRequestDto;
import com.cooper.demoatmapp.account.repository.AccountRepository;
import com.cooper.demoatmapp.user.domain.User;
import com.cooper.demoatmapp.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountDepositIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("계좌에 돈을 입금한다")
    @Test
    void deposit() throws Exception {
        //given
        String accountNumber = "001-00-123456789";
        String password = "password";
        String userId = "cooperId";
        String username = "coopername";
        String name = "cooper";
        String phoneNumber = "010-0000-0000";
        String email = "cooper@email.com";

        accountRepository.save(Account.create(accountNumber, password, userId));
        userRepository.save(User.create(name, username, phoneNumber, email));

        AccountDepositRequestDto accountDepositRequestDto = new AccountDepositRequestDto(accountNumber, password, BigInteger.valueOf(1000));
        String requestBody = objectMapper.writeValueAsString(accountDepositRequestDto);

        //when
        ResultActions result = mockMvc.perform(put("/api/v1/accounts/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.data.accountNumber").value(accountNumber),
                jsonPath("$.data.balance").value(1000)
        );
    }

    @DisplayName("비밀번호가 일치하지 않을 경우 예외를 반환한다")
    @Test
    void throwAccountPasswordNotMatchException() throws Exception {
        //given
        String accountNumber = "001-00-123456789";
        String password = "password";
        String userId = "cooperId";
        String username = "coopername";
        String name = "cooper";
        String phoneNumber = "010-0000-0000";
        String email = "cooper@email.com";
        String wrongPassword = "wrongPassword";

        accountRepository.save(Account.create(accountNumber, password, userId));
        userRepository.save(User.create(name, username, phoneNumber, email));

        AccountDepositRequestDto accountDepositRequestDto = new AccountDepositRequestDto(accountNumber, wrongPassword, BigInteger.valueOf(1000));
        String requestBody = objectMapper.writeValueAsString(accountDepositRequestDto);

        //when
        ResultActions result = mockMvc.perform(put("/api/v1/accounts/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        assertAll(
                () -> result.andExpect(status().isBadRequest()),
                () -> jsonPath("$.httpStatus").value(400),
                () -> jsonPath("$.message").isNotEmpty()
        );
    }

    @DisplayName("계좌번호가 일치하지 않는 경우 경우 예외를 반환한다")
    @Test
    void throwAccountNotFoundException() throws Exception {
        //given
        String accountNumber = "001-00-123456789";
        String wrongAccountNumber = "001-00-987654321";
        String password = "password";
        String userId = "cooperId";
        String username = "coopername";
        String name = "cooper";
        String phoneNumber = "010-0000-0000";
        String email = "cooper@email.com";

        accountRepository.save(Account.create(accountNumber, password, userId));
        userRepository.save(User.create(name, username, phoneNumber, email));

        AccountDepositRequestDto accountDepositRequestDto
                = new AccountDepositRequestDto(wrongAccountNumber, password, BigInteger.valueOf(1000));
        String requestBody = objectMapper.writeValueAsString(accountDepositRequestDto);

        //when
        ResultActions result = mockMvc.perform(put("/api/v1/accounts/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        result.andExpectAll(
                status().isBadRequest(),
                jsonPath("$.httpStatus").value(400),
                jsonPath("$.message").isNotEmpty()
        );

    }

}