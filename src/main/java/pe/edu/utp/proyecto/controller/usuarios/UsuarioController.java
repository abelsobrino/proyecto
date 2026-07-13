package pe.edu.utp.proyecto.controller.usuarios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.usuarios.Usuario;
import pe.edu.utp.proyecto.service.usuarios.UsuarioService;

import java.util.List;

/**
 * Controlador REST para la gestion de usuarios del sistema.
 */
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Gestion de usuarios del sistema")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Crea un nuevo usuario.
     * @param usuario Datos del usuario.
     * @return Usuario creado.
     */
    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> crearUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("POST /usuarios - Creando nuevo usuario");
        Usuario creado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Usuario creado exitosamente"));
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado o 404.
     */
    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerUsuario(@PathVariable Integer id) {
        log.info("GET /usuarios/{} - Obteniendo usuario", id);
        return usuarioService.obtenerUsuarioPorId(id)
                .map(usuario -> ResponseEntity.ok(ApiResponse.success(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los usuarios.
     * @return Lista de usuarios.
     */
    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> obtenerTodosUsuarios() {
        log.info("GET /usuarios - Obteniendo todos los usuarios");
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(ApiResponse.success(usuarios));
    }

    /**
     * Actualiza un usuario existente.
     * @param id ID del usuario.
     * @param usuario Datos actualizados.
     * @return Usuario actualizado.
     */
    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody Usuario usuario) {
        log.info("PUT /usuarios/{} - Actualizando usuario", id);
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Usuario actualizado exitosamente"));
    }

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario a eliminar.
     */
    @Operation(summary = "Eliminar un usuario por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable Integer id) {
        log.info("DELETE /usuarios/{} - Eliminando usuario", id);
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Usuario eliminado exitosamente"));
    }

    /**
     * Busca un usuario por correo.
     * @param correo Correo del usuario.
     * @return Usuario encontrado o 404.
     */
    @Operation(summary = "Buscar usuario por correo")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<Usuario>> buscarPorCorreo(@RequestParam String correo) {
        log.info("GET /usuarios/buscar - Buscando usuario por correo: {}", correo);
        return usuarioService.buscarPorCorreo(correo)
                .map(usuario -> ResponseEntity.ok(ApiResponse.success(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista usuarios activos.
     * @return Lista de usuarios activos.
     */
    @Operation(summary = "Obtener usuarios activos")
    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<Usuario>>> obtenerActivos() {
        log.info("GET /usuarios/activos - Obteniendo usuarios activos");
        List<Usuario> usuarios = usuarioService.obtenerUsuariosActivos();
        return ResponseEntity.ok(ApiResponse.success(usuarios));
    }

    /**
     * Lista usuarios por rol.
     * @param idRol ID del rol.
     * @return Lista de usuarios con ese rol.
     */
    @Operation(summary = "Obtener usuarios por rol")
    @GetMapping("/rol/{idRol}")
    public ResponseEntity<ApiResponse<List<Usuario>>> obtenerUsuariosPorRol(@PathVariable Integer idRol) {
        log.info("GET /usuarios/rol/{} - Obteniendo usuarios por rol", idRol);
        List<Usuario> usuarios = usuarioService.obtenerUsuariosPorRol(idRol);
        return ResponseEntity.ok(ApiResponse.success(usuarios));
    }
}