package com.comit.notjpa.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
	@Bean
	public Logger configureLogging() {
		Logger logger = Logger.getLogger("org.springframework.security");
		logger.setLevel(Level.FINE); // Thiết lập mức log là FINE (tương đương DEBUG)
		System.out.println("Logging for Spring Security is set to DEBUG level.");
		return logger;
	}
}
