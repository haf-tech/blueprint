package com.haddouti.pg.blueprint.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.haddouti.pg.blueprint.note.jpa.JPAPersistenceConfig;

@SpringBootApplication
@Configuration
@Import({ JPAPersistenceConfig.class, SwaggerConfig.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
