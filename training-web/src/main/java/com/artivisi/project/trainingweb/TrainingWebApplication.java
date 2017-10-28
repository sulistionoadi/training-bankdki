package com.artivisi.project.trainingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.artivisi.project.trainingmodel", "com.artivisi.project.trainingweb"})
public class TrainingWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingWebApplication.class, args);
	}
}
