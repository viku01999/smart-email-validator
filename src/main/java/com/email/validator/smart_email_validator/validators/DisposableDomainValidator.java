package com.email.validator.smart_email_validator.validators;

import com.email.validator.smart_email_validator.entity.ValidationDetail;
import com.email.validator.smart_email_validator.provider.DisposableDomainProvider;
import com.email.validator.smart_email_validator.strategy.EmailValidationStrategy;
import com.email.validator.smart_email_validator.utils.EmailUtils;
import org.springframework.stereotype.Component;

@Component
public class DisposableDomainValidator extends BaseValidator implements EmailValidationStrategy {

    private final DisposableDomainProvider disposableDomainProvider;

    public DisposableDomainValidator(DisposableDomainProvider disposableDomainProvider) {
        this.disposableDomainProvider = disposableDomainProvider;
    }

    @Override
    public ValidationDetail validate(String email) {
        String domain = EmailUtils.extractDomain(email);
        if (domain == null) {
            return build("Disposable Check", false, "Invalid email format", 0.5);
        }

        boolean allowed = !disposableDomainProvider.isDisposable(domain);

        return build("Disposable Check",
                allowed,
                allowed ? "Trusted domain" : "Disposable domain",
                allowed ? 0.0 : 0.5);
    }
}
