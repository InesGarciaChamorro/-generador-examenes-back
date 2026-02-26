package com.ProyectoPracticas.demo.usuarios.test;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioCreateDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioDeleteDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioDetailDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioListDTO;
import com.ProyectoPracticas.demo.domain.dtos.usuarios.UsuarioUpdateDTO;
import com.ProyectoPracticas.demo.domain.entities.roles.RolEntity;
import com.ProyectoPracticas.demo.domain.entities.usuarios.UsuarioEntity;
import com.ProyectoPracticas.demo.domain.repositories.roles.RolRepository;
import com.ProyectoPracticas.demo.domain.repositories.usuarios.UsuarioRepository;
import com.ProyectoPracticas.demo.presentation.services.usuarios.UsuarioServiceImplementado;

/**
 * Pruebas unitarias para UsuarioServiceImplementado.
 * Al usar inyección por constructor en el servicio, podemos crear la instancia
 * directamente pasando los mocks nosotros mismos.
 */
class UsuarioServiceImplementadoTest {

    /** Mocks de las dependencias del servicio. Se usan Mockito para simular el comportamiento
     * de los repositorios y el model mapper sin necesidad de una base de datos real o configuración de Spring.
     */
    private final UsuarioRepository repo = mock(UsuarioRepository.class);
    private final RolRepository rolRepo = mock(RolRepository.class);
    private final ModelMapper modelMapper = mock(ModelMapper.class);

    /** Instancia del servicio a probar, creada con los mocks. Al no usar @InjectMocks, controlamos
	 * exactamente qué instancia se crea y con qué dependencias, lo que es más claro y directo.
	 */
    private final UsuarioServiceImplementado service =
            new UsuarioServiceImplementado(repo, rolRepo, modelMapper);

    /**
     * Resetea los mocks antes de cada test para que no haya interferencias entre pruebas.
     */
    @BeforeEach
    void resetMocks() {
        reset(repo, rolRepo, modelMapper);
    }

    /**
     * Crea un UsuarioDetailDTO con valores seguros en todos sus campos.
     * Se usan valores explícitos para los campos que tienen anotaciones de
     * validación incompatibles con su tipo (activo: Integer con @NotBlank).
     */
    private UsuarioDetailDTO crearDetailDTO() {
        return Instancio.of(UsuarioDetailDTO.class)
                .set(field(UsuarioDetailDTO.class, "idUsuario"),       "1")
                .set(field(UsuarioDetailDTO.class, "nombreUsuario"),   "Ines")
                .set(field(UsuarioDetailDTO.class, "apellidoUsuario"), "Garcia Chamorro")
                .set(field(UsuarioDetailDTO.class, "correoUsuario"),   "ines@gmail.com")
                .set(field(UsuarioDetailDTO.class, "activo"),          1)
                .create();
    }

    /**
     * Crea un UsuarioUpdateDTO base sin contraseña ni rol (ambos a null),
     * para poder añadir solo el campo que interese en cada test.
     * Igual que antes, activo es Integer con @NotBlank, así que se fija a mano.
     */
    private UsuarioUpdateDTO crearUpdateDTOVacio() {
        return Instancio.of(UsuarioUpdateDTO.class)
                .ignore(field(UsuarioUpdateDTO.class, "contrasenhaUsuario"))
                .ignore(field(UsuarioUpdateDTO.class, "nombreRol"))
                .set(field(UsuarioUpdateDTO.class,   "activo"), 1)
                .create();
    }

    

    /**
     * Verifica que listar() devuelve correctamente la lista de todos los usuarios.
     */
    @Test
    void listarTodosLosUsuarios() {
        UsuarioEntity  entity = Instancio.create(UsuarioEntity.class);
        UsuarioListDTO dto    = Instancio.of(UsuarioListDTO.class)
                .set(field(UsuarioListDTO.class, "idUsuario"),       "1")
                .set(field(UsuarioListDTO.class, "nombreUsuario"),   "Ines")
                .set(field(UsuarioListDTO.class, "apellidoUsuario"), "Garcia Chamorro")
                .set(field(UsuarioListDTO.class, "correoUsuario"),   "ines@gmail.com")
                .create();

        when(repo.findAll()).thenReturn(List.of(entity));
        when(modelMapper.map(entity, UsuarioListDTO.class)).thenReturn(dto);

        List<UsuarioListDTO> resultado = service.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ines", resultado.get(0).getNombreUsuario());
        verify(repo).findAll();
    }

    /**
     * Verifica que listar() devuelve lista vacía si no hay usuarios en la base de datos.
     */
    @Test
    void listarSinUsuarios() {
        when(repo.findAll()).thenReturn(List.of());

        List<UsuarioListDTO> resultado = service.listar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    /**
     * Verifica que obtenerPorId() devuelve el DTO correcto cuando el usuario existe.
     */
    @Test
    void obtenerPorIdUsuarioExistente() {
        UsuarioEntity    entity    = Instancio.create(UsuarioEntity.class);
        UsuarioDetailDTO detailDTO = crearDetailDTO();

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, UsuarioDetailDTO.class)).thenReturn(detailDTO);

        UsuarioDetailDTO resultado = service.obtenerPorId(entity.getIdUsuario());

        assertNotNull(resultado);
        assertEquals(detailDTO.getIdUsuario(), resultado.getIdUsuario());
    }

    /**
     * Verifica que obtenerPorId() lanza excepción cuando el usuario no existe.
     */
    @Test
    void obtenerPorIdUsuarioNoExistente() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.obtenerPorId(99L));

        assertEquals("Usuario no encontrado por id", ex.getMessage());
    }


    /**
     * Verifica que crear() guarda el usuario y devuelve su DTO de detalle.
     */
    @Test
    void crearDatosValidos() {
        UsuarioCreateDTO createDTO      = Instancio.create(UsuarioCreateDTO.class);
        UsuarioEntity    entityGuardada = Instancio.create(UsuarioEntity.class);
        UsuarioDetailDTO detailDTO      = crearDetailDTO();

        when(repo.save(any(UsuarioEntity.class))).thenReturn(entityGuardada);
        when(modelMapper.map(entityGuardada, UsuarioDetailDTO.class)).thenReturn(detailDTO);

        UsuarioDetailDTO resultado = service.crear(createDTO);

        assertNotNull(resultado);
        verify(repo).save(any(UsuarioEntity.class));
    }

    /**
     * Verifica que crear() siempre establece activo=1 en el nuevo usuario,
     * independientemente de lo que venga en el DTO.
     */
    @Test
    void crearActivoEn1() {
        UsuarioCreateDTO createDTO = Instancio.create(UsuarioCreateDTO.class);
        UsuarioDetailDTO detailDTO = crearDetailDTO();

        when(repo.save(any(UsuarioEntity.class))).thenAnswer(invocation -> {
            UsuarioEntity u = invocation.getArgument(0);
            assertEquals(1, u.getActivo(), "El nuevo usuario debe crearse con activo=1");
            return u;
        });
        when(modelMapper.map(any(UsuarioEntity.class), eq(UsuarioDetailDTO.class)))
                .thenReturn(detailDTO);

        service.crear(createDTO);

        verify(repo).save(any(UsuarioEntity.class));
    }

    /**
     * Verifica que un ADMIN no puede cambiar la contraseña.
     */
    @Test
    void actualizar_adminIntentaCambiarContrasenha_debeLanzarExcepcion() {
        UsuarioEntity    entity = Instancio.create(UsuarioEntity.class);
        UsuarioUpdateDTO dto    = crearUpdateDTOVacio();
        dto.setContrasenhaUsuario("nuevaPass"); // ADMIN intenta cambiar contraseña

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.actualizar(entity.getIdUsuario(), dto, "ADMIN"));

        assertEquals("El admin no puede cambiar la contraseña", ex.getMessage());
    }

    /**
     * Verifica que un USUARIO no puede cambiar el rol.
     */
    @Test
    void actualizar_usuarioIntentaCambiarRol_debeLanzarExcepcion() {
        UsuarioEntity    entity = Instancio.create(UsuarioEntity.class);
        UsuarioUpdateDTO dto    = crearUpdateDTOVacio();
        dto.setNombreRol("ADMIN"); // USUARIO intenta cambiar rol

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.actualizar(entity.getIdUsuario(), dto, "USUARIO"));

        assertEquals("Solo el admin puede cambiar el rol de un usuario", ex.getMessage());
    }

    /**
     * Verifica que un PROFESOR no puede cambiar el rol.
     */
    @Test
    void actualizarProfesorIntentaCambiarRol() {
        UsuarioEntity    entity = Instancio.create(UsuarioEntity.class);
        UsuarioUpdateDTO dto    = crearUpdateDTOVacio();
        dto.setNombreRol("ADMIN"); // PROFESOR intenta cambiar rol

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.actualizar(entity.getIdUsuario(), dto, "PROFESOR"));

        assertEquals("Solo el admin puede cambiar el rol de un usuario", ex.getMessage());
    }

    /**
     * Verifica que un USUARIO puede cambiar su propia contraseña.
     */
    @Test
    void actualizarUsuarioCambiaContrasenha() {
        UsuarioEntity    entity    = Instancio.create(UsuarioEntity.class);
        UsuarioDetailDTO detailDTO = crearDetailDTO();
        UsuarioUpdateDTO dto       = crearUpdateDTOVacio();
        dto.setContrasenhaUsuario("nuevaPass");

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));
        when(repo.save(any(UsuarioEntity.class))).thenReturn(entity);
        when(modelMapper.map(entity, UsuarioDetailDTO.class)).thenReturn(detailDTO);

        UsuarioDetailDTO resultado = service.actualizar(entity.getIdUsuario(), dto, "USUARIO");

        assertNotNull(resultado);
        verify(repo).save(any(UsuarioEntity.class));
    }

    /**
     * Verifica que un ADMIN puede asignar un nuevo rol a un usuario cuando el rol existe.
     */
    @Test
    void actualizarAdminCambiaRol() {
        UsuarioEntity    entity    = Instancio.create(UsuarioEntity.class);
        UsuarioDetailDTO detailDTO = crearDetailDTO();
        RolEntity nuevoRol = Instancio.of(RolEntity.class)
                .set(field(RolEntity.class, "nombreRol"), "PROFESOR")
                .create();
        UsuarioUpdateDTO dto = crearUpdateDTOVacio();
        dto.setNombreRol("PROFESOR");

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));
        when(rolRepo.findByNombreRol("PROFESOR")).thenReturn(Optional.of(nuevoRol));
        when(repo.save(any(UsuarioEntity.class))).thenReturn(entity);
        when(modelMapper.map(entity, UsuarioDetailDTO.class)).thenReturn(detailDTO);

        UsuarioDetailDTO resultado = service.actualizar(entity.getIdUsuario(), dto, "ADMIN");

        assertNotNull(resultado);
        verify(rolRepo).findByNombreRol("PROFESOR");
    }

    /**
     * Verifica que actualizar() lanza excepción si el rol indicado no existe en la BD.
     */
    @Test
    void actualizarAdminRolInexistente() {
        UsuarioEntity    entity = Instancio.create(UsuarioEntity.class);
        UsuarioUpdateDTO dto    = crearUpdateDTOVacio();
        dto.setNombreRol("ROL_FALSO");

        when(repo.findById(entity.getIdUsuario())).thenReturn(Optional.of(entity));
        when(rolRepo.findByNombreRol("ROL_FALSO")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.actualizar(entity.getIdUsuario(), dto, "ADMIN"));

        assertEquals("Rol no encontrado por nombre", ex.getMessage());
    }

    /**
     * Verifica que actualizar() lanza excepción si el usuario no existe en la BD.
     */
    @Test
    void actualizarUsuarioNoExiste() {
        UsuarioUpdateDTO dto = crearUpdateDTOVacio();

        when(repo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.actualizar(99L, dto, "ADMIN"));

        assertEquals("Usuario no encontrado por actualizar", ex.getMessage());
    }

    /**
     * Verifica que eliminar() hace borrado lógico (activo=0) y devuelve el DTO correcto.
     */
    @Test
    void eliminarUsuarioExiste() {
        UsuarioEntity entity = Instancio.of(UsuarioEntity.class)
                .set(field(UsuarioEntity.class, "idUsuario"),      1L)
                .set(field(UsuarioEntity.class, "nombreUsuario"),  "Ines")
                .set(field(UsuarioEntity.class, "apellidoUsuario"),"Garcia Chamorro")
                .set(field(UsuarioEntity.class, "activo"),         1)
                .create();

        when(repo.findById(1L)).thenReturn(Optional.of(entity));
        when(repo.save(any(UsuarioEntity.class))).thenAnswer(invocation -> {
            UsuarioEntity u = invocation.getArgument(0);
            assertEquals(0, u.getActivo(), "Tras eliminar, activo debe ser 0");
            return u;
        });

        UsuarioDeleteDTO resultado = service.eliminar(1L);

        assertNotNull(resultado);
        assertEquals(1L,      resultado.getIdUsuario());
        assertEquals("Ines", resultado.getNombreUsuario());
        assertEquals("Garcia Chamorro",   resultado.getApellidoUsuario());
        verify(repo).save(any(UsuarioEntity.class));
    }

    /**
     * Verifica que eliminar() lanza excepción si el usuario no existe.
     */
    @Test
    void eliminarUsuarioNoExiste() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.eliminar(99L));

        assertEquals("Usuario no encontrado por eliminar", ex.getMessage());
    }
}