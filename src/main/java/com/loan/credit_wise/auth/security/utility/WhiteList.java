package com.loan.credit_wise.auth.security.utility;

public class WhiteList {
    public static String[] freeEndpoints() {
        return new String[]{
                "/api/v1/auth/login",
        };
    }

    public static String[] swagger() {
        return new String[]{
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs",
                "/v3/api-docs/**"
        };
    }
}
