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
import pe.edu.utp.proyecto.modelo.usuarios.Rol;
import pe.edu.utp.proyecto.service.usuarios.RolService;
import java.util.List;

@RestController
@RequestMapping("/usuarios/roles")
@Tag(name = "Roles", description = "Gestion de roles del sistema")
@RequiredArgsConstructor
@Slf4j
public class RolController {

    private final RolService rolService;

    @Operation(summary = "Crear un nuevo rol")
    @PostMapping
    public ResponseEntity<ApiResponse<Rol>> crearRol(@Valid @RequestBody Rol rol) {
        log.info("POST /usuarios/roles - Creando nuevo rol");
        Rol creado = rolService.guardarRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Rol creado exitosamente"));
    }

    @Operation(summary = "Obtener un rol por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Rol>> obtenerRol(@PathVariable Integer id) {
        log.info("GET /usuarios/roles/{} - Obteniendo rol", id);
        return rolService.obtenerRolPorId(id)
                .map(rol -> ResponseEntity.ok(ApiResponse.success(rol)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los roles")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Rol>>> obtenerTodosRoles() {
        log.info("GET /usuarios/roles - Obteniendo todos los roles");
        List<Rol> roles = rolService.obtenerTodosLosRoles();
        return ResponseEntity.ok(ApiResponse.success(roles));
    }

    @Operation(summary = "Actualizar un rol existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Rol>> actualizarRol(
            @PathVariable Integer id,
            @Valid @RequestBody Rol rol) {
        log.info("PUT /usuarios/roles/{} - Actualizando rol", id);
        Rol actualizado = rolService.actualizarRol(id, rol);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Rol actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un rol por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarRol(@PathVariable Integer id) {
        log.info("DELETE /usuarios/roles/{} - Eliminando rol", id);
        rolService.eliminarRol(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Rol eliminado exitosamente"));
    }
}