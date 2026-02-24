package com.ProyectoPracticas.demo.hola;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ProyectoPracticas.demo.ProyectoPracticasApplication.AppConfig;
import com.ProyectoPracticas.demo.ProyectoPracticasApplication.HelloWorld;

public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
		context.close();
	}
}
