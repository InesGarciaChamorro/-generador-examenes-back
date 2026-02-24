package com.ProyectoPracticas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



/* En caso de que pidan con SpringBoot, usamos un controller:
package com.ProyectoPracticas.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	
	@RequestMapping("/api")
	@GetMapping("/")
	public String holaMundo(){
		return "Hola Mundo";
	}
}
*/