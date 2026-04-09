package com.email.validator.smart_email_validator.utils;

import com.email.validator.smart_email_validator.enums.EmailStatus;

public class StatusDecide {

    public static EmailStatus calculateStatus(double score) {
        if (score >= 0.8) {
            return EmailStatus.VALID;
        } else if (score >= 0.5) {
            return EmailStatus.RISKY;
        } else {
            return EmailStatus.INVALID;
        }
    }

}