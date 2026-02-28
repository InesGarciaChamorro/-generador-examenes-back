package com.ProyectoPracticas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ProyectoPracticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPracticasApplication.class, args);
	}
	
	public static class HelloWorld {
		private String message;
		public void setMessage(String message) {
			this.message = message;
		}
		public void getMessage() {
			System.out.println("My Message : " + message);
		}
	}
	
	@Configuration
	public static class AppConfig{
		@Bean
		public HelloWorld helloWorld() {
			HelloWorld helloWorld = new HelloWorld();
			helloWorld.setMessage("Hello World");
			return helloWorld;
		}
	}
	
}
