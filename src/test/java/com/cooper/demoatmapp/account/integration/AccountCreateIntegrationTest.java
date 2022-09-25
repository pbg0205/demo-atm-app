package com.cooper.demoatmapp.account.integration;


import com.cooper.demoatmapp.account.dto.AccountCreateRequestDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountCreateIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("계좌가 정상적으로 생성된다")
    @Test
    void createAccount() throws Exception {
        //given
        String name = "이름";
        String username = "아이디";
        String phoneNumber = "010-0000-0000";
        String email = "cooper@naver.com";
        String password = "1234";

        AccountCreateRequestDto accountCreateRequestDto
                = new AccountCreateRequestDto(name, username, phoneNumber, email, password);
        String requestBody = objectMapper.writeValueAsString(accountCreateRequestDto);

        //when
        ResultActions result = mockMvc.perform(post("/api/v1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then
        result.andExpectAll(
                status().isOk(),
                jsonPath("$.httpStatus").value(200),
                jsonPath("$.data.name").value(name),
                jsonPath("$.data.phoneName").value(phoneNumber),
                jsonPath("$.data.email").value(email),
                jsonPath("$.data.accountNumber").exists()
        );
    }

}