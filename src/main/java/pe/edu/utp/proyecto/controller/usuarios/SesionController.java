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
import pe.edu.utp.proyecto.modelo.usuarios.Sesion;
import pe.edu.utp.proyecto.service.usuarios.SesionService;

import java.util.List;

/**
 * Controlador REST para la gestion de sesiones de usuarios.
 */
@RestController
@RequestMapping("/usuarios/sesiones")
@Tag(name = "Sesiones", description = "Gestion de sesiones de usuarios")
@RequiredArgsConstructor
@Slf4j
public class SesionController {

    private final SesionService sesionService;

    /**
     * Crea una nueva sesion.
     * @param sesion Datos de la sesion.
     * @return Sesion creada.
     */
    @Operation(summary = "Crear una nueva sesion")
    @PostMapping
    public ResponseEntity<ApiResponse<Sesion>> crearSesion(@Valid @RequestBody Sesion sesion) {
        log.info("POST /usuarios/sesiones - Creando nueva sesion");
        Sesion creada = sesionService.guardarSesion(sesion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Sesion creada exitosamente"));
    }

    /**
     * Obtiene una sesion por su ID.
     * @param id ID de la sesion.
     * @return Sesion encontrada o 404.
     */
    @Operation(summary = "Obtener una sesion por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Sesion>> obtenerSesion(@PathVariable Integer id) {
        log.info("GET /usuarios/sesiones/{} - Obteniendo sesion", id);
        return sesionService.obtenerSesionPorId(id)
                .map(sesion -> ResponseEntity.ok(ApiResponse.success(sesion)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todas las sesiones.
     * @return Lista de sesiones.
     */
    @Operation(summary = "Obtener todas las sesiones")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Sesion>>> obtenerTodasSesiones() {
        log.info("GET /usuarios/sesiones - Obteniendo todas las sesiones");
        List<Sesion> sesiones = sesionService.obtenerTodasLasSesiones();
        return ResponseEntity.ok(ApiResponse.success(sesiones));
    }

    /**
     * Actualiza una sesion existente.
     * @param id ID de la sesion.
     * @param sesion Datos actualizados.
     * @return Sesion actualizada.
     */
    @Operation(summary = "Actualizar una sesion existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Sesion>> actualizarSesion(
            @PathVariable Integer id,
            @Valid @RequestBody Sesion sesion) {
        log.info("PUT /usuarios/sesiones/{} - Actualizando sesion", id);
        Sesion actualizada = sesionService.actualizarSesion(id, sesion);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Sesion actualizada exitosamente"));
    }

    /**
     * Elimina una sesion por su ID.
     * @param id ID de la sesion a eliminar.
     */
    @Operation(summary = "Eliminar una sesion por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarSesion(@PathVariable Integer id) {
        log.info("DELETE /usuarios/sesiones/{} - Eliminando sesion", id);
        sesionService.eliminarSesion(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Sesion eliminada exitosamente"));
    }

    /**
     * Lista sesiones activas.
     * @return Lista de sesiones activas.
     */
    @Operation(summary = "Obtener sesiones activas")
    @GetMapping("/activas")
    public ResponseEntity<ApiResponse<List<Sesion>>> obtenerSesionesActivas() {
        log.info("GET /usuarios/sesiones/activas - Obteniendo sesiones activas");
        List<Sesion> sesiones = sesionService.obtenerSesionesActivas();
        return ResponseEntity.ok(ApiResponse.success(sesiones));
    }

    /**
     * Lista sesiones de un usuario especifico.
     * @param idUsuario ID del usuario.
     * @return Lista de sesiones del usuario.
     */
    @Operation(summary = "Obtener sesiones de un usuario")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<List<Sesion>>> obtenerSesionesPorUsuario(@PathVariable Integer idUsuario) {
        log.info("GET /usuarios/sesiones/usuario/{} - Obteniendo sesiones del usuario", idUsuario);
        List<Sesion> sesiones = sesionService.obtenerSesionesPorUsuario(idUsuario);
        return ResponseEntity.ok(ApiResponse.success(sesiones));
    }
}