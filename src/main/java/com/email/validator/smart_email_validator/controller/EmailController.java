package com.email.validator.smart_email_validator.controller;

import com.email.validator.smart_email_validator.dto.EmailResponse;
import com.email.validator.smart_email_validator.service.EmailValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailValidationService emailValidationService;

    public EmailController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @GetMapping("/validate")
    public ResponseEntity<EmailResponse> validate(@RequestParam("email") String email) {
        return ResponseEntity.ok(emailValidationService.validate(email));
    }

}
