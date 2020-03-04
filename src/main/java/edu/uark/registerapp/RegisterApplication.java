package edu.uark.registerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan("controllers")
public class RegisterApplication {

	public static void main(final String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

}
