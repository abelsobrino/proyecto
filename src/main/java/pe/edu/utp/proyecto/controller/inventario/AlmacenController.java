package pe.edu.utp.proyecto.controller.inventario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.inventario.Almacen;
import pe.edu.utp.proyecto.service.inventario.AlmacenService;

import java.util.List;

/**
 * Controlador REST para la gestion de almacenes.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/inventario/almacenes")
@Tag(name = "Almacenes", description = "Gestion de almacenes")
@RequiredArgsConstructor
@Slf4j
public class AlmacenController {

    private final AlmacenService almacenService;

    @Operation(summary = "Crear un nuevo almacen")
    @PostMapping
    public ResponseEntity<ApiResponse<Almacen>> crearAlmacen(@Valid @RequestBody Almacen almacen) {
        log.info("POST /inventario/almacenes - Creando nuevo almacen");
        Almacen creado = almacenService.guardarAlmacen(almacen);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Almacen creado exitosamente"));
    }

    @Operation(summary = "Obtener un almacen por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Almacen>> obtenerAlmacen(@PathVariable Integer id) {
        log.info("GET /inventario/almacenes/{} - Obteniendo almacen", id);
        return almacenService.obtenerAlmacenPorId(id)
                .map(almacen -> ResponseEntity.ok(ApiResponse.success(almacen)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los almacenes")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Almacen>>> obtenerTodosAlmacenes() {
        log.info("GET /inventario/almacenes - Obteniendo todos los almacenes");
        List<Almacen> almacenes = almacenService.obtenerTodosLosAlmacenes();
        return ResponseEntity.ok(ApiResponse.success(almacenes));
    }

    @Operation(summary = "Actualizar un almacen existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Almacen>> actualizarAlmacen(
            @PathVariable Integer id,
            @Valid @RequestBody Almacen almacen) {
        log.info("PUT /inventario/almacenes/{} - Actualizando almacen", id);
        Almacen actualizado = almacenService.actualizarAlmacen(id, almacen);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Almacen actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un almacen por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarAlmacen(@PathVariable Integer id) {
        log.info("DELETE /inventario/almacenes/{} - Eliminando almacen", id);
        almacenService.eliminarAlmacen(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Almacen eliminado exitosamente"));
    }

    @Operation(summary = "Buscar almacenes por nombre que contenga")
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<Almacen>>> buscarPorNombre(@PathVariable String nombre) {
        log.info("GET /inventario/almacenes/buscar/nombre/{} - Buscando por nombre", nombre);
        List<Almacen> almacenes = almacenService.buscarPorNombre(nombre);
        return ResponseEntity.ok(ApiResponse.success(almacenes));
    }

    @Operation(summary = "Buscar almacenes por responsable que contenga")
    @GetMapping("/buscar/responsable/{responsable}")
    public ResponseEntity<ApiResponse<List<Almacen>>> buscarPorResponsable(@PathVariable String responsable) {
        log.info("GET /inventario/almacenes/buscar/responsable/{} - Buscando por responsable", responsable);
        List<Almacen> almacenes = almacenService.buscarPorResponsable(responsable);
        return ResponseEntity.ok(ApiResponse.success(almacenes));
    }
}