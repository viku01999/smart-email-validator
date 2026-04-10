package com.email.validator.smart_email_validator.validators;

import com.email.validator.smart_email_validator.entity.ValidationDetail;
import com.email.validator.smart_email_validator.strategy.EmailValidationStrategy;
import com.email.validator.smart_email_validator.utils.EmailUtils;
import org.springframework.stereotype.Component;

@Component
public class DomainPatternValidator extends BaseValidator implements EmailValidationStrategy {

    @Override
    public ValidationDetail validate(String email) {
        String domain = EmailUtils.extractDomain(email);

        boolean suspicious =
                domain.contains("temp") ||
                        domain.contains("mail") ||
                        domain.endsWith(".xyz") ||
                        domain.endsWith(".top");

        return build("Pattern Check",
                !suspicious,
                suspicious ? "Suspicious domain pattern" : "Normal domain",
                suspicious ? 0.4 : 0.0);
    }
}
