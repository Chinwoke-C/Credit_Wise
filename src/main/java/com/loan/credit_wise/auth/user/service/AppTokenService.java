package com.loan.credit_wise.auth.user.service;

import com.loan.credit_wise.auth.user.data.models.AppToken;

public interface AppTokenService {
    void saveToken(AppToken token);

}
