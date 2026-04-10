package com.email.validator.smart_email_validator.validators;

import com.email.validator.smart_email_validator.entity.ValidationDetail;
import com.email.validator.smart_email_validator.strategy.EmailValidationStrategy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleBasedValidator extends BaseValidator implements EmailValidationStrategy {

    private static final Set<String> ROLE_PREFIXES = Set.of(
            "admin", "support", "info", "sales", "contact", "help"
    );


    @Override
    public ValidationDetail validate(String email) {
        String prefix = email.split("@")[0].toLowerCase();

        boolean isRole = ROLE_PREFIXES.contains(prefix);

        return build(
                "Role Check",
                !isRole,
                isRole ? "Role-based email" : "Personal email",
                isRole ? 0.3 : 0.0
        );
    }
}
