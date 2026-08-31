package com.tomas.demo;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
    
    @PostConstruct
        public void init() {
            
            TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
