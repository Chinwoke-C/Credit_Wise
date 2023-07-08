package com.loan.credit_wise.auth.user.service;

import com.loan.credit_wise.auth.user.data.models.User;
import com.loan.credit_wise.auth.user.data.repositories.UserRepository;
import com.loan.credit_wise.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

    }
}
