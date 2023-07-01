package com.loan.credit_wise.auth.security.filter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.credit_wise.auth.user.data.models.User;
import com.loan.credit_wise.auth.user.service.AppTokenService;
import com.loan.credit_wise.exception.CreditWiseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AppTokenService appTokenService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
        throws AuthenticationException{
    try{
        User user = objectMapper.readValue(request.getInputStream(), User.class);

    Authentication authentication =
                new UsernamePasswordAuthenticationToken(user.getEmail(),
                        user.getPassword());
    Authentication authenticationResult =
            authenticationManager.authenticate(authentication);
    if(authenticationResult != null){
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return SecurityContextHolder.getContext().getAuthentication();
    }
    } catch (IOException e) {
        throw new CreditWiseException("Authentication failed");
    }
    throw new CreditWiseException("Authentication failed");
}
}