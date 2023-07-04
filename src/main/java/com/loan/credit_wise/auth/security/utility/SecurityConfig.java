package com.loan.credit_wise.auth.security.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.credit_wise.auth.security.filter.AppAuthenticationFilter;
import com.loan.credit_wise.auth.security.filter.AppAuthorizationFilter;
import com.loan.credit_wise.auth.user.service.AppTokenService;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final AppTokenService appTokenService;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .cors(
                        Customizer.withDefaults()
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authenticationEntryPoint)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,
                                WhiteList.freeEndpoints())
                        .permitAll()
                        .requestMatchers(WhiteList.swagger()
                        ).permitAll()
                        .anyRequest().authenticated())
                .addFilterAt(
                        login(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        new AppAuthorizationFilter(
                                userDetailsService,
                                appTokenService,
                                jwtService
                        ),
                        AppAuthenticationFilter.class
                )
                .build();
    }

    private UsernamePasswordAuthenticationFilter login() {
        UsernamePasswordAuthenticationFilter authenticationFilter =
                new AppAuthenticationFilter(
                        authenticationManager,
                        appTokenService,
                        userDetailsService,
                        objectMapper,
                        jwtService
                );
        authenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
        return authenticationFilter;
    }
}
