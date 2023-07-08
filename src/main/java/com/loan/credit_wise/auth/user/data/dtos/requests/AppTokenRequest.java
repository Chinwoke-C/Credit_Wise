package com.loan.credit_wise.auth.user.data.dtos.requests;

import com.loan.credit_wise.auth.user.data.models.User;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppTokenRequest {
    private String accessToken;
    private String refreshToken;
    private User user;
}
