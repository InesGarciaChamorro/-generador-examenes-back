# Este proyecto implementa el subsistema de gestión de usuarios, correspondiente al primer sprint.
Incluye las operaciones básicas sobre usuarios, reglas de negocio para la actualización según rol y pruebas unitarias.


### Descripción del proyecto
El sistema permite gestionar usuarios a través de una API REST desarrollada en Spring Boot.
La información se almacena en una base de datos MySQL y se accede mediante JPA.
Se incluyen las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y una lógica de negocio que controla qué campos puede modificar cada rol (ADMIN, PROFESOR y USUARIO).
Se han incorporado DTOs para separar las entidades internas de las respuestas de la API, y se ha utilizado ModelMapper para simplificar la conversión entre capas.
También se han desarrollado pruebas unitarias utilizando JUnit e Instancio.


### Reglas de negocio para la actualización de usuarios
Las reglas de negocio para la actualización de usuarios se aplican según el rol del usuario.
- Si el usuario tiene el rol "ADMIN", puede cambiar el rol del usuario pero no su contraseña. Además, puede actualizar su nombre, apellidos, correo electrónico y estado activo.
- Si el usuario tiene el rol "PROFESOR" o "USUARIO", puede cambiar su contraseña pero no su rol. Además, puede actualizar su nombre, apellidos, correo electrónico y estado activo.


### API
La API de gestión de usuarios incluye los siguientes endpoints:
1. GET /usuarios: Devuelve una lista de todos los usuarios registrados en el sistema.
2. GET /usuarios/{id}: Devuelve la información de un usuario específico según su ID.
3. POST /usuarios: Permite crear un nuevo usuario con la información proporcionada en el cuerpo de la solicitud.
4. PUT /usuarios/{id}: Permite actualizar la información de un usuario existente según su ID, aplicando las reglas de negocio correspondientes al rol del usuario.
5. DELETE /usuarios/{id}: Permite eliminar un usuario existente según su ID. Establece el campo "activo" del usuario a false en lugar de eliminarlo físicamente de la base de datos.


### Swagger
Se ha integrado Swagger para documentar la API de gestión de usuarios.
La documentación de Swagger se puede acceder a través de la URL: http://localhost:8080/swagger-ui/index.html


### Tests unitarios
Las pruebas unitarias se realizan con JUnit 5 e Instancio (generación de datos de prueba).
Casos a cubrir:
- Creación de un nuevo usuario con datos válidos.
- Creación de un nuevo usuario con datos inválidos (por ejemplo, correo electrónico no válido).
- Actualización válida según rol (ADMIN/PROFESOR/USUARIO).
- Actualización inválida según rol (por ejemplo, un USUARIO intentando cambiar el rol).
- Eliminación lógica de un usuario existente.
- Comportamiento al consultar/eliminar un usuario inexistente.

