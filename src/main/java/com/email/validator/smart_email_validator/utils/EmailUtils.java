package com.email.validator.smart_email_validator.utils;

public class EmailUtils {

    public static String extractDomain(String email) {
        if (!email.contains("@")) {
            return null;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return null;
        }

        return parts[1];
    }

}
