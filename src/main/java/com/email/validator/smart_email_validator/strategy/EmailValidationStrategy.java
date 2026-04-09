package com.email.validator.smart_email_validator.strategy;

import com.email.validator.smart_email_validator.entity.ValidationDetail;

// STRATEGY PATTERN
public interface EmailValidationStrategy {

    ValidationDetail validate(String email);

}


