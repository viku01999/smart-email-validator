package com.email.validator.smart_email_validator.service;

import com.email.validator.smart_email_validator.dto.EmailResponse;
import com.email.validator.smart_email_validator.entity.EmailRecord;
import com.email.validator.smart_email_validator.entity.ValidationDetail;
import com.email.validator.smart_email_validator.provider.DisposableDomainProvider;
import com.email.validator.smart_email_validator.repository.EmailRecordRepository;
import com.email.validator.smart_email_validator.strategy.EmailValidationStrategy;
import com.email.validator.smart_email_validator.utils.EmailUtils;
import com.email.validator.smart_email_validator.utils.StatusDecide;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmailValidationService {

    private final EmailRecordRepository emailRecordRepository;
    private final List<EmailValidationStrategy> emailValidationStrategies;

    public EmailValidationService(
            EmailRecordRepository emailRecordRepository,
            List<EmailValidationStrategy> emailValidationStrategies) {

        this.emailRecordRepository = emailRecordRepository;
        this.emailValidationStrategies = emailValidationStrategies;
    }

    public EmailResponse validate(String email) {

        Optional<EmailRecord> existing = emailRecordRepository.findByEmail(email);

        if (existing.isPresent()) {
            return map(existing.get());
        }

        EmailRecord emailRecord = new EmailRecord();
        String domain = EmailUtils.extractDomain(email);

        emailRecord.setEmail(email);
        emailRecord.setDomain(domain);
        emailRecord.setCreatedAt(Instant.now());

        List<ValidationDetail> validationDetails = new ArrayList<>();

        boolean isValid = true;
        double score = 1.0;

        for (EmailValidationStrategy strategy : emailValidationStrategies) {
            try {
                ValidationDetail detail = strategy.validate(email);

                detail.setEmailRecord(emailRecord);
                validationDetails.add(detail);

                if (!detail.getIsPassed()) {
                    isValid = false;
                    score -= detail.getImpactScore();
                }

            } catch (Exception e) {

                ValidationDetail error = new ValidationDetail();
                error.setCheckName("System Error");
                error.setIsPassed(false);
                error.setMessage("Validator failed internally");
                error.setImpactScore(1.0);
                error.setEmailRecord(emailRecord);

                validationDetails.add(error);

                isValid = false;
                score -= 1.0;
            }
        }

        if (score < 0) score = 0;

        emailRecord.setIsValid(isValid);
        emailRecord.setScore(score);
        emailRecord.setValidationDetails(validationDetails);
        emailRecord.setEmailStatus(StatusDecide.calculateStatus(score));

        emailRecordRepository.save(emailRecord);

        return map(emailRecord);
    }

    private EmailResponse map(EmailRecord record) {

        EmailResponse response = new EmailResponse();

        response.setEmail(record.getEmail());
        response.setDomain(record.getDomain());
        response.setIsValid(record.getIsValid());
        response.setScore(record.getScore());
        response.setEmailStatus(record.getEmailStatus());

        return response;
    }
}