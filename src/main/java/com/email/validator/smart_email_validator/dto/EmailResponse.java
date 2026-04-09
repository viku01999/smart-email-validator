package com.email.validator.smart_email_validator.dto;

import com.email.validator.smart_email_validator.enums.EmailStatus;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class EmailResponse {
    private String email;
    private Boolean isValid;
    private Boolean isDisposable;
    private Boolean isSpam;
    private Double score;
    private String domain;
    private Instant createdAt;
    private EmailStatus emailStatus;
    private List<ValidationDetailDto> details;
}
