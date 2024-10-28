package com.gestor.tareas.com.gestor.tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@SpringBootApplication
public class AppGestorTareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGestorTareasApplication.class, args);
	}

}
