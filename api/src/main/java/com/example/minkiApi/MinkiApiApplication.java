package com.example.minkiApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MinkiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinkiApiApplication.class, args);
	}

}
