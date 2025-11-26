package com.andre.projetobanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetobancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetobancoApplication.class, args);
	}

}
