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
import pe.edu.utp.proyecto.modelo.usuarios.Permiso;
import pe.edu.utp.proyecto.service.usuarios.PermisoService;

import java.util.List;

/**
 * Controlador REST para la gestion de permisos.
 *
 * @author Sistema de Ventas UTP
 */
@RestController
@RequestMapping("/usuarios/permisos")
@Tag(name = "Permisos", description = "Gestion de permisos del sistema")
@RequiredArgsConstructor
@Slf4j
public class PermisoController {

    private final PermisoService permisoService;

    /** Crea un nuevo permiso */
    @Operation(summary = "Crear un nuevo permiso")
    @PostMapping
    public ResponseEntity<ApiResponse<Permiso>> crearPermiso(@Valid @RequestBody Permiso permiso) {
        log.info("POST /usuarios/permisos - Creando nuevo permiso");
        Permiso creado = permisoService.guardarPermiso(permiso);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Permiso creado exitosamente"));
    }

    /** Obtiene un permiso por su ID */
    @Operation(summary = "Obtener un permiso por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Permiso>> obtenerPermiso(@PathVariable Integer id) {
        log.info("GET /usuarios/permisos/{} - Obteniendo permiso", id);
        return permisoService.obtenerPermisoPorId(id)
                .map(permiso -> ResponseEntity.ok(ApiResponse.success(permiso)))
                .orElse(ResponseEntity.notFound().build());
    }

    /** Lista todos los permisos */
    @Operation(summary = "Obtener todos los permisos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Permiso>>> obtenerTodosPermisos() {
        log.info("GET /usuarios/permisos - Obteniendo todos los permisos");
        List<Permiso> permisos = permisoService.obtenerTodosLosPermisos();
        return ResponseEntity.ok(ApiResponse.success(permisos));
    }

    /** Actualiza un permiso existente */
    @Operation(summary = "Actualizar un permiso existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Permiso>> actualizarPermiso(
            @PathVariable Integer id,
            @Valid @RequestBody Permiso permiso) {
        log.info("PUT /usuarios/permisos/{} - Actualizando permiso", id);
        Permiso actualizado = permisoService.actualizarPermiso(id, permiso);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Permiso actualizado exitosamente"));
    }

    /** Elimina un permiso por su ID */
    @Operation(summary = "Eliminar un permiso por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPermiso(@PathVariable Integer id) {
        log.info("DELETE /usuarios/permisos/{} - Eliminando permiso", id);
        permisoService.eliminarPermiso(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Permiso eliminado exitosamente"));
    }
}