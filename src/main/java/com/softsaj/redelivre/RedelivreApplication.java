package com.softsaj.redelivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.softsaj")
@SpringBootApplication
public class RedelivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedelivreApplication.class, args);
	}

}
