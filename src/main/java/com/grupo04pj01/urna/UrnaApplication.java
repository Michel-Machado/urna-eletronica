package com.grupo04pj01.urna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.grupo04pj01.urna"})
public class UrnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrnaApplication.class, args);
	}

}
