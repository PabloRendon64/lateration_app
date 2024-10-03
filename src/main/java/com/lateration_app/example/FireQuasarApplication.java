package com.lateration_app.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FireQuasarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireQuasarApplication.class, args);
	}

}
