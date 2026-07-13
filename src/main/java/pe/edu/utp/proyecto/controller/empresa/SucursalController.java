package pe.edu.utp.proyecto.controller.empresa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.empresa.Sucursal;
import pe.edu.utp.proyecto.service.empresa.SucursalService;

import java.util.List;

/**
 * Controlador REST para la gestion de sucursales.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/empresa/sucursales")
@Tag(name = "Sucursales", description = "Gestion de sucursales")
@RequiredArgsConstructor
@Slf4j
public class SucursalController {

    private final SucursalService sucursalService;

    @Operation(summary = "Crear una nueva sucursal")
    @PostMapping
    public ResponseEntity<ApiResponse<Sucursal>> crearSucursal(@Valid @RequestBody Sucursal sucursal) {
        log.info("POST /empresa/sucursales - Creando nueva sucursal");
        Sucursal creada = sucursalService.guardarSucursal(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Sucursal creada exitosamente"));
    }

    @Operation(summary = "Obtener una sucursal por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Sucursal>> obtenerSucursal(@PathVariable Integer id) {
        log.info("GET /empresa/sucursales/{} - Obteniendo sucursal", id);
        return sucursalService.obtenerSucursalPorId(id)
                .map(sucursal -> ResponseEntity.ok(ApiResponse.success(sucursal)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las sucursales")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Sucursal>>> obtenerTodasSucursales() {
        log.info("GET /empresa/sucursales - Obteniendo todas las sucursales");
        List<Sucursal> sucursales = sucursalService.obtenerTodasLasSucursales();
        return ResponseEntity.ok(ApiResponse.success(sucursales));
    }

    @Operation(summary = "Actualizar una sucursal existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Sucursal>> actualizarSucursal(
            @PathVariable Integer id,
            @Valid @RequestBody Sucursal sucursal) {
        log.info("PUT /empresa/sucursales/{} - Actualizando sucursal", id);
        Sucursal actualizada = sucursalService.actualizarSucursal(id, sucursal);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Sucursal actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una sucursal por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarSucursal(@PathVariable Integer id) {
        log.info("DELETE /empresa/sucursales/{} - Eliminando sucursal", id);
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Sucursal eliminada exitosamente"));
    }

    @Operation(summary = "Buscar sucursales por nombre que contenga")
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<Sucursal>>> buscarPorNombre(@PathVariable String nombre) {
        log.info("GET /empresa/sucursales/buscar/nombre/{} - Buscando por nombre", nombre);
        List<Sucursal> sucursales = sucursalService.buscarPorNombre(nombre);
        return ResponseEntity.ok(ApiResponse.success(sucursales));
    }

    @Operation(summary = "Buscar sucursales por direccion que contenga")
    @GetMapping("/buscar/direccion/{direccion}")
    public ResponseEntity<ApiResponse<List<Sucursal>>> buscarPorDireccion(@PathVariable String direccion) {
        log.info("GET /empresa/sucursales/buscar/direccion/{} - Buscando por direccion", direccion);
        List<Sucursal> sucursales = sucursalService.buscarPorDireccion(direccion);
        return ResponseEntity.ok(ApiResponse.success(sucursales));
    }
}