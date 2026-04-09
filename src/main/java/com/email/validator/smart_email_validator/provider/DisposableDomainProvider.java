package com.email.validator.smart_email_validator.provider;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class DisposableDomainProvider {

    // This ensures all threads always see the latest set immediately after swap.
    private volatile Set<String> blockedDomains = new HashSet<>();

    @PostConstruct
    public void load() throws IOException {
        blockedDomains = loadFromFile();
        System.out.println("Loaded domains: " + blockedDomains.size());
    }

    public void reload() throws IOException {
        Set<String> newDomains = loadFromFile();
        blockedDomains = newDomains;
        System.out.println("Reloaded domains: " + blockedDomains.size());
    }

    private Set<String> loadFromFile() throws IOException {
        Set<String> domains = new HashSet<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("blocked_domains.txt");

        if (inputStream == null) {
            throw new RuntimeException("blocked_domains.txt not found!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {

                line = line.trim().toLowerCase();

                if (!line.isBlank()) {
                    domains.add(line);
                }
            }
        }

        return domains;
    }

    public boolean isDisposable(String domain) {
        return blockedDomains.contains(domain)
                || blockedDomains.stream().anyMatch(domain::endsWith);
    }

}
