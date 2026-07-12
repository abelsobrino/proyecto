package pe.edu.utp.proyecto.controller.ventas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;
import pe.edu.utp.proyecto.service.ventas.DetalleVentaService;
import java.util.List;

@RestController
@RequestMapping("/ventas/detalles")
@Tag(name = "Detalles de Venta", description = "Gestion de detalles de venta")
@RequiredArgsConstructor
@Slf4j
public class DetalleVentaController {

    private final DetalleVentaService detalleService;

    @Operation(summary = "Crear un nuevo detalle de venta")
    @PostMapping
    public ResponseEntity<ApiResponse<DetalleVenta>> crearDetalle(@Valid @RequestBody DetalleVenta detalle) {
        log.info("POST /ventas/detalles - Creando nuevo detalle de venta");
        DetalleVenta creado = detalleService.guardarDetalle(detalle);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Detalle creado exitosamente"));
    }

    @Operation(summary = "Obtener un detalle por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleVenta>> obtenerDetalle(@PathVariable Integer id) {
        log.info("GET /ventas/detalles/{} - Obteniendo detalle", id);
        return detalleService.obtenerDetallePorId(id)
                .map(detalle -> ResponseEntity.ok(ApiResponse.success(detalle)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los detalles")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DetalleVenta>>> obtenerTodosDetalles() {
        log.info("GET /ventas/detalles - Obteniendo todos los detalles");
        List<DetalleVenta> detalles = detalleService.obtenerTodosLosDetalles();
        return ResponseEntity.ok(ApiResponse.success(detalles));
    }

    @Operation(summary = "Actualizar un detalle existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleVenta>> actualizarDetalle(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVenta detalle) {
        log.info("PUT /ventas/detalles/{} - Actualizando detalle", id);
        DetalleVenta actualizado = detalleService.actualizarDetalle(id, detalle);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Detalle actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un detalle por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarDetalle(@PathVariable Integer id) {
        log.info("DELETE /ventas/detalles/{} - Eliminando detalle", id);
        detalleService.eliminarDetalle(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Detalle eliminado exitosamente"));
    }

    @Operation(summary = "Calcular subtotal de un detalle")
    @PostMapping("/calcular")
    public ResponseEntity<ApiResponse<DetalleVenta>> calcularSubtotal(@Valid @RequestBody DetalleVenta detalle) {
        log.info("POST /ventas/detalles/calcular - Calculando subtotal del detalle");
        DetalleVenta calculado = detalleService.calcularSubtotal(detalle);
        return ResponseEntity.ok(ApiResponse.success(calculado, "Subtotal calculado exitosamente"));
    }
}