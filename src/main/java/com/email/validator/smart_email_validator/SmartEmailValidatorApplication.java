package com.email.validator.smart_email_validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SmartEmailValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartEmailValidatorApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady(){
		System.out.println("Server started at http://localhost:4563");
	}

}
