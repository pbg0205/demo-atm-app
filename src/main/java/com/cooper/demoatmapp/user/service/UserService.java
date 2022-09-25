package com.cooper.demoatmapp.user.service;

import com.cooper.demoatmapp.user.domain.User;
import com.cooper.demoatmapp.user.dto.UserLookupResponseDto;
import com.cooper.demoatmapp.user.dto.UserRegisterRequestDto;
import com.cooper.demoatmapp.user.dto.UserRegisterResponseDto;
import com.cooper.demoatmapp.user.exception.UserAlreadyExistenceException;
import com.cooper.demoatmapp.user.exception.UserNotFoundException;
import com.cooper.demoatmapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserLookupResponseDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return UserLookupResponseDto.fromEntity(user);
    }

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        if(userRepository.existsByUsername(userRegisterRequestDto.getUsername())) {
            throw new UserAlreadyExistenceException();
        }

        User registerUser = userRepository.save(userRegisterRequestDto.toEntity());
        return UserRegisterResponseDto.fromEntity(registerUser);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
