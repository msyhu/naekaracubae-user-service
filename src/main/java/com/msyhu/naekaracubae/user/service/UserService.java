package com.msyhu.naekaracubae.user.service;

import com.msyhu.naekaracubae.user.domain.user.UserRepository;
import com.msyhu.naekaracubae.user.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }
}
