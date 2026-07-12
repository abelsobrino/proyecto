package pe.edu.utp.proyecto.controller.usuarios_controller;

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

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Gestion de usuarios del sistema")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> crearUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("POST /usuarios - Creando nuevo usuario");
        Usuario creado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Usuario creado exitosamente"));
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerUsuario(@PathVariable Integer id) {
        log.info("GET /usuarios/{} - Obteniendo usuario", id);
        return usuarioService.obtenerUsuarioPorId(id)
                .map(usuario -> ResponseEntity.ok(ApiResponse.success(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> obtenerTodosUsuarios() {
        log.info("GET /usuarios - Obteniendo todos los usuarios");
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(ApiResponse.success(usuarios));
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody Usuario usuario) {
        log.info("PUT /usuarios/{} - Actualizando usuario", id);
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Usuario actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un usuario por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable Integer id) {
        log.info("DELETE /usuarios/{} - Eliminando usuario", id);
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Usuario eliminado exitosamente"));
    }

    @Operation(summary = "Buscar usuario por correo")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<Usuario>> buscarPorCorreo(@RequestParam String correo) {
        log.info("GET /usuarios/buscar - Buscando usuario por correo: {}", correo);
        return usuarioService.buscarPorCorreo(correo)
                .map(usuario -> ResponseEntity.ok(ApiResponse.success(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener usuarios activos")
    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<Usuario>>> obtenerActivos() {
        log.info("GET /usuarios/activos - Obteniendo usuarios activos");
        List<Usuario> usuarios = usuarioService.obtenerUsuariosActivos();
        return ResponseEntity.ok(ApiResponse.success(usuarios));
    }
}