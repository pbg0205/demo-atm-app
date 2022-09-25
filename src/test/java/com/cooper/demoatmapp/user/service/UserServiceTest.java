package com.cooper.demoatmapp.user.service;

import com.cooper.demoatmapp.user.domain.User;
import com.cooper.demoatmapp.user.dto.UserLookupResponseDto;
import com.cooper.demoatmapp.user.dto.UserRegisterRequestDto;
import com.cooper.demoatmapp.user.dto.UserRegisterResponseDto;
import com.cooper.demoatmapp.user.exception.UserAlreadyExistenceException;
import com.cooper.demoatmapp.user.exception.UserNotFoundException;
import com.cooper.demoatmapp.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userService = new UserService(userRepository);
    }

    @DisplayName("유저를 등록한다")
    @Test
    void registerUser() {
        //given
        User user = User.create("cooper", "cooperId", "010-0000-0000", "cooper@email.com");
        user.setId("userId");

        given(userRepository.existsByUsername(anyString())).willReturn(false);
        given(userRepository.save(any())).willReturn(user);

        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .name("cooper")
                .username("cooperId")
                .phoneNumber("010-0000-0000")
                .email("cooper@email.com")
                .build();

        //when
        UserRegisterResponseDto userRegisterResponseDto = userService.registerUser(userRegisterRequestDto);

        //then
        assertThat(userRegisterResponseDto.getUserId()).isNotEmpty();
    }

    @DisplayName("유저 아이디가 존재할 경우 예외를 반환한다")
    @Test
    void throwExceptionWhenUsernameExist() {
        //given
        given(userRepository.existsByUsername(anyString())).willReturn(true);
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .name("cooper")
                .username("cooperId")
                .phoneNumber("010-0000-0000")
                .email("cooper@email.com")
                .build();

        //when, then
        assertThatThrownBy(() -> userService.registerUser(userRegisterRequestDto))
                .isInstanceOf(UserAlreadyExistenceException.class);
    }

    @DisplayName("유저 아이디를 기반으로 회원을 조회한다")
    @Test
    void findByUsername() {
        //given
        String phoneNumber = "010-0000-0000";
        String username = "cooperId";
        String name = "cooper";
        String email = "cooper@email.com";

        User user = User.create(name, username, phoneNumber, email);
        given(userRepository.findByUsername(any())).willReturn(Optional.of(user));

        //when
        UserLookupResponseDto userLookupResponseDto = userService.findByUsername(name);

        //then
        assertAll(
                () -> assertThat(userLookupResponseDto.getName()).isEqualTo(name),
                () -> assertThat(userLookupResponseDto.getEmail()).isEqualTo(email),
                () -> assertThat(userLookupResponseDto.getPhoneNumber()).isEqualTo(phoneNumber)
        );
    }

    @DisplayName("유저가 존재하지 않을 경우 예외를 반환한다")
    @Test
    void throwUserNotFoundException() {
        //given
        given(userRepository.findByUsername(any())).willThrow(UserNotFoundException.class);

        //when, then
        assertThatThrownBy(() -> userService.findByUsername("cooper")).isInstanceOf(UserNotFoundException.class);
    }

}