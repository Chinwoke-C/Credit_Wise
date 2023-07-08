package com.loan.credit_wise.auth.user.service;

import com.loan.credit_wise.auth.user.data.models.User;

public interface UserService {
    User findUserByEmail(String email);
}
