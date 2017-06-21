package com.haddouti.pg.blueprint.web;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StatsApplication {

	@GetMapping("/ping")
	public String ping() {
		return "ping";
	}
	

	@GetMapping("/ping/verbose")
	public String pingVerbose() {
		return "ping - " + new Date();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StatsApplication.class, args); 
	}
}
