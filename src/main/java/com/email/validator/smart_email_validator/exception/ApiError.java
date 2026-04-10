package com.email.validator.smart_email_validator.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiError {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Object data;

    public ApiError(int status, String error, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ApiError(int status, String error, String message, String path, Object data) {
        this(status, error, message, path);
        this.data = data;
    }

}
