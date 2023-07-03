package com.loan.credit_wise.auth.security.filter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.credit_wise.auth.security.user.AuthenticatedUser;
import com.loan.credit_wise.auth.security.utility.AuthenticationToken;
import com.loan.credit_wise.auth.security.utility.JwtService;
import com.loan.credit_wise.auth.user.data.models.AppToken;
import com.loan.credit_wise.auth.user.data.models.User;
import com.loan.credit_wise.auth.user.service.AppTokenService;
import com.loan.credit_wise.exception.CreditWiseException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AppTokenService appTokenService;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;

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
    @Override
    protected void successfulAuthentication(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain chain,
          Authentication authResult) throws IOException{

        Map<String, Object> claims = new HashMap<>();
        authResult.getAuthorities().forEach(
                role -> claims.put("claim", role)
        );
        String email = authResult.getPrincipal().toString();
        String accessToken = jwtService.generateAccessToken(claims, email);
        String refreshToken = jwtService.generateRefreshToken(email);

        AuthenticatedUser authenticatedUser =
                (AuthenticatedUser) userDetailsService.loadUserByUsername(email);

    AppToken token = AppToken.builder()
            .user(authenticatedUser.getUser())
            .refreshToken(refreshToken)
            .accessToken(accessToken)
            .revoked(false)
            .build();
    appTokenService.saveToken(token);

    AuthenticationToken authenticationToken =
            AuthenticationToken.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), authenticationToken);
    }

}