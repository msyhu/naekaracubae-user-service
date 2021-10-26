package com.msyhu.naekaracubae.user.service;

import com.msyhu.naekaracubae.user.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }
}
