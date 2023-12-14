package com.sonssoft.consultation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ConsultationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultationApplication.class, args);
	}

}
