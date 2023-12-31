package com.loan.credit_wise.auth.security.utility;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationToken {
    private String accessToken;
    private String refreshToken;
}
