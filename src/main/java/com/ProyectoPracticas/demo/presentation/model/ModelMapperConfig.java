package com.ProyectoPracticas.demo.presentation.model;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* * Configuración de ModelMapper para la aplicación.
 * Esta clase define un bean de ModelMapper que se puede inyectar en otras partes de la aplicación para realizar mapeos entre objetos.
 */
@Configuration
public class ModelMapperConfig {

	/* * Método que crea y devuelve una instancia de ModelMapper.
	 * ModelMapper es una biblioteca que facilita el mapeo entre objetos, especialmente útil para convertir entre entidades y DTOs.
	 */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}