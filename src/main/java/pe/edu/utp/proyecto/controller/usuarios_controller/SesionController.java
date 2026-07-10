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
import pe.edu.utp.proyecto.modelo.usuarios.Sesion;
import pe.edu.utp.proyecto.service.usuarios_service.SesionService;
import java.util.List;

@RestController
@RequestMapping("/usuarios/sesiones")
@Tag(name = "Sesiones", description = "Gestion de sesiones de usuarios")
@RequiredArgsConstructor
@Slf4j
public class SesionController {

    private final SesionService sesionService;

    @Operation(summary = "Crear una nueva sesion")
    @PostMapping
    public ResponseEntity<ApiResponse<Sesion>> crearSesion(@Valid @RequestBody Sesion sesion) {
        log.info("POST /usuarios/sesiones - Creando nueva sesion");
        Sesion creada = sesionService.guardarSesion(sesion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Sesion creada exitosamente"));
    }

    @Operation(summary = "Obtener una sesion por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Sesion>> obtenerSesion(@PathVariable Integer id) {
        log.info("GET /usuarios/sesiones/{} - Obteniendo sesion", id);
        return sesionService.obtenerSesionPorId(id)
                .map(sesion -> ResponseEntity.ok(ApiResponse.success(sesion)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las sesiones")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Sesion>>> obtenerTodasSesiones() {
        log.info("GET /usuarios/sesiones - Obteniendo todas las sesiones");
        List<Sesion> sesiones = sesionService.obtenerTodasLasSesiones();
        return ResponseEntity.ok(ApiResponse.success(sesiones));
    }

    @Operation(summary = "Actualizar una sesion existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Sesion>> actualizarSesion(
            @PathVariable Integer id,
            @Valid @RequestBody Sesion sesion) {
        log.info("PUT /usuarios/sesiones/{} - Actualizando sesion", id);
        Sesion actualizada = sesionService.actualizarSesion(id, sesion);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Sesion actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una sesion por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarSesion(@PathVariable Integer id) {
        log.info("DELETE /usuarios/sesiones/{} - Eliminando sesion", id);
        sesionService.eliminarSesion(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Sesion eliminada exitosamente"));
    }

    @Operation(summary = "Obtener sesiones activas")
    @GetMapping("/activas")
    public ResponseEntity<ApiResponse<List<Sesion>>> obtenerSesionesActivas() {
        log.info("GET /usuarios/sesiones/activas - Obteniendo sesiones activas");
        List<Sesion> sesiones = sesionService.obtenerSesionesActivas();
        return ResponseEntity.ok(ApiResponse.success(sesiones));
    }
}