package com.loan.credit_wise.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CreditWiseException{
    public UserNotFoundException() {
        this("User not found");
    }

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
