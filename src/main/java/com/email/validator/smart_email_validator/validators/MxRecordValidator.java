package com.email.validator.smart_email_validator.validators;

import com.email.validator.smart_email_validator.entity.ValidationDetail;
import com.email.validator.smart_email_validator.strategy.EmailValidationStrategy;
import com.email.validator.smart_email_validator.utils.EmailUtils;
import org.springframework.stereotype.Component;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

@Component
public class MxRecordValidator extends BaseValidator implements EmailValidationStrategy {
    @Override
    public ValidationDetail validate(String email) {

        String domain = EmailUtils.extractDomain(email);
        if (domain == null) {
            return build("MX Check", false, "Invalid email format", 0.6);
        }

        boolean hasMx = hasMXRecord(domain);

        return build("MX Check",
                hasMx,
                hasMx ? "Mail server found" : "No mail server (invalid domain)",
                hasMx ? 0.0 : 0.6);
    }

    private boolean hasMXRecord(String domain) {
        try {
            Lookup lookup = new Lookup(domain, Type.MX);
            Record[] records = lookup.run();
            return records != null && records.length > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
