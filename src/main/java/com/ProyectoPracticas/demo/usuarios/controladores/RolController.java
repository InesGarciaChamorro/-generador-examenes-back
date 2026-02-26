package com.ProyectoPracticas.demo.usuarios.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

/* * Controlador REST para la gestión de roles en el sistema.
 * Proporciona endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los roles disponibles.
 * Utiliza el servicio RolService para manejar la lógica de negocio relacionada con los roles.
 * Incluye anotaciones de Swagger para documentar la API y facilitar su uso por parte de los desarrolladores.
 */
@RestController
@RequestMapping("/usuarios/roles")
@Tag(name = "Roles", description = "Operaciones CRUD sobre roles")
public class RolController {
	
}
