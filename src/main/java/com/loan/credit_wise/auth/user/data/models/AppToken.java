package com.loan.credit_wise.auth.user.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppToken {
    @Id
    @GeneratedValue
    private Long id;
    private String accessToken;
    private String refreshToken;
    private boolean revoked;
    private boolean expired;
    private final LocalDateTime createdAt = LocalDateTime.now();
    @OneToOne
    private User user;


}
