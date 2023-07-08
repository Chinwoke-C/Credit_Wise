package com.loan.credit_wise.auth.security.user;

import com.loan.credit_wise.auth.user.data.models.User;
import com.loan.credit_wise.auth.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userService.findUserByEmail(username);
        return new AuthenticatedUser(user);
    }
}
