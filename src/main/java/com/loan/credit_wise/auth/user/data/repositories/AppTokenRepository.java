package com.loan.credit_wise.auth.user.data.repositories;

import com.loan.credit_wise.auth.user.data.models.AppToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTokenRepository extends JpaRepository<AppToken, Long> {
}
