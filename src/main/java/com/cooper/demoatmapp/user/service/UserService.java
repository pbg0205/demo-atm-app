package com.cooper.demoatmapp.user.service;

import com.cooper.demoatmapp.user.domain.User;
import com.cooper.demoatmapp.user.dto.UserRegisterRequestDto;
import com.cooper.demoatmapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByNameAndAndPhoneNumber (String name, String phoneNumber) {
        return userRepository.findUserByNameAndAndPhoneNumber(name, phoneNumber);
    }

    public User createUser(UserRegisterRequestDto userRegisterRequestDto) {
        return userRepository.save(userRegisterRequestDto.toEntity());
    }

}
