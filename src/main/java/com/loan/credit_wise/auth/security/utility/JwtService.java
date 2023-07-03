package com.loan.credit_wise.auth.security.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Map;

@Component
public class JwtService {
    @Value("${access_expiration}")
    private long accessExpiration;
    @Value("${refresh_expiration}")
    private long refreshExpiration;
    private final Key key;

    @Autowired
    public JwtService(Key key){
        this.key = key;
    }

    public String generateAccessToken(Map<String, Object> claims, String email) {
    }

    public String generateRefreshToken(String email) {
    }
}
