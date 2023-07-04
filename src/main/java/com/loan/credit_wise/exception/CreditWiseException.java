package com.loan.credit_wise.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CreditWiseException extends RuntimeException{
    @Getter
    private final HttpStatus status;

    public CreditWiseException() {
        this("An error occurred");
    }

    public CreditWiseException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public CreditWiseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
