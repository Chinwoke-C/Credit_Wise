package com.loan.credit_wise.auth.user.service;

import com.loan.credit_wise.auth.user.data.models.AppToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppTokenServiceImpl implements AppTokenService{
    @Override
    public void saveToken(AppToken token) {

    }
}
