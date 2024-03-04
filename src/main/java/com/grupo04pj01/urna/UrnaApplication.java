package com.grupo04pj01.urna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class UrnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrnaApplication.class, args);
	}

}
