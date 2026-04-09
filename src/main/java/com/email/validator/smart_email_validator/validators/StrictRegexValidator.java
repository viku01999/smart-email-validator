package com.email.validator.smart_email_validator.validators;

import com.email.validator.smart_email_validator.entity.ValidationDetail;
import com.email.validator.smart_email_validator.strategy.EmailValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class StrictRegexValidator extends BaseValidator implements EmailValidationStrategy {

    private static final String REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";


    @Override
    public ValidationDetail validate(String email) {
        boolean match = email.matches(REGEX);

        return build(
                "Regex Check",
                match,
                match ? "Valid format" : "Invalid email format",
                match ? 0.0 : 0.4
        );
    }
}
