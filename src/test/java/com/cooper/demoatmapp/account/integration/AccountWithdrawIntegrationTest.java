package com.cooper.demoatmapp.account.integration;

import com.cooper.demoatmapp.account.domain.Account;
import com.cooper.demoatmapp.account.domain.Money;
import com.cooper.demoatmapp.account.dto.AccountWithdrawalRequestDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AccountWithdrawIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("정상 계좌 출금을 확인한다")
    @Test
    void withdraw() throws Exception {
        //given
        String accountNumber = "001-00-123456789";
        String password = "password";
        String userId = "cooperId";
        String username = "coopername";
        String name = "cooper";
        String phoneNumber = "010-0000-0000";
        String email = "cooper@email.com";

        Account account = Account.create(accountNumber, password, userId);
        account.depositMoney(Money.of(BigInteger.valueOf(10_000)));

        userRepository.save(User.create(name, username, phoneNumber, email));
        accountRepository.save(account);

        AccountWithdrawalRequestDto accountDepositRequestDto = new AccountWithdrawalRequestDto(accountNumber, password, BigInteger.valueOf(1000));
        String requestBody = objectMapper.writeValueAsString(accountDepositRequestDto);

        //when
        ResultActions result = mockMvc.perform(put("/api/v1/accounts/withdrawal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.data.accountNumber").value(accountNumber),
                jsonPath("$.data.balance").value(9000)
        );
    }

    @DisplayName("계좌 잔액이 출금보다 적을 경우 예외를 반환한다")
    @Test
    void throwNegativeMoneyException() throws Exception {
        //given
        String accountNumber = "001-00-987654321";
        String password = "password2";
        String userId = "cooperId2";
        String username = "coopername";
        String name = "cooper1";
        String phoneNumber = "010-0000-0000";
        String email = "cooper@email.com";

        Account account = Account.create(accountNumber, password, userId);
        account.depositMoney(Money.of(BigInteger.valueOf(1_000)));

        userRepository.save(User.create(name, username, phoneNumber, email));
        accountRepository.save(account);

        AccountWithdrawalRequestDto accountDepositRequestDto
                = new AccountWithdrawalRequestDto(accountNumber, password, BigInteger.valueOf(10_000));
        String requestBody = objectMapper.writeValueAsString(accountDepositRequestDto);

        //when
        ResultActions result = mockMvc.perform(put("/api/v1/accounts/withdrawal")
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
