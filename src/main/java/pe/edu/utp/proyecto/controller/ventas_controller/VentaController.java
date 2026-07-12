package pe.edu.utp.proyecto.controller.ventas_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.ventas.Venta;
import pe.edu.utp.proyecto.service.ventas.impl.VentaServiceImpl;
import pe.edu.utp.proyecto.service.ventas.VentaService;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@Tag(name = "Ventas", description = "Gestion de ventas")
@RequiredArgsConstructor
@Slf4j
public class VentaController {

    private final VentaService ventaService;
    private final VentaServiceImpl ventaServiceImpl;

    @Operation(summary = "Crear una nueva venta")
    @PostMapping
    public ResponseEntity<ApiResponse<Venta>> crearVenta(@Valid @RequestBody Venta venta) {
        log.info("POST /ventas - Creando nueva venta");
        Venta creada = ventaService.guardarVenta(venta);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Venta creada exitosamente"));
    }

    @Operation(summary = "Obtener una venta por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Venta>> obtenerVenta(@PathVariable Integer id) {
        log.info("GET /ventas/{} - Obteniendo venta", id);
        return ventaService.obtenerVentaPorId(id)
                .map(venta -> ResponseEntity.ok(ApiResponse.success(venta)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las ventas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venta>>> obtenerTodasVentas() {
        log.info("GET /ventas - Obteniendo todas las ventas");
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ApiResponse.success(ventas));
    }

    @Operation(summary = "Actualizar una venta existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Venta>> actualizarVenta(
            @PathVariable Integer id,
            @Valid @RequestBody Venta venta) {
        log.info("PUT /ventas/{} - Actualizando venta", id);
        Venta actualizada = ventaService.actualizarVenta(id, venta);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Venta actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una venta por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarVenta(@PathVariable Integer id) {
        log.info("DELETE /ventas/{} - Eliminando venta", id);
        ventaService.eliminarVenta(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Venta eliminada exitosamente"));
    }

    @Operation(summary = "Calcular total de una venta")
    @GetMapping("/{id}/total")
    public ResponseEntity<ApiResponse<Double>> calcularTotal(@PathVariable Integer id) {
        log.info("GET /ventas/{}/total - Calculando total de venta", id);
        Double total = ventaService.calcularTotalVenta(id);
        return ResponseEntity.ok(ApiResponse.success(total, "Total calculado exitosamente"));
    }

    // =============================================
    // METODOS DEL PATRON STATE
    // =============================================

    // USO DEL PATRON STATE
    @Operation(summary = "Procesar una venta (cambia a estado PROCESANDO)")
    @PutMapping("/{id}/procesar")
    public ResponseEntity<ApiResponse<Venta>> procesarVenta(@PathVariable Integer id) {
        log.info("PUT /ventas/{}/procesar - Procesando venta", id);
        Venta venta = ventaServiceImpl.procesarVenta(id);
        return ResponseEntity.ok(ApiResponse.success(venta, "Venta procesada exitosamente"));
    }

    // USO DEL PATRON STATE
    @Operation(summary = "Completar una venta (cambia a estado COMPLETADA)")
    @PutMapping("/{id}/completar")
    public ResponseEntity<ApiResponse<Venta>> completarVenta(@PathVariable Integer id) {
        log.info("PUT /ventas/{}/completar - Completando venta", id);
        Venta venta = ventaServiceImpl.completarVenta(id);
        return ResponseEntity.ok(ApiResponse.success(venta, "Venta completada exitosamente"));
    }

    // USO DEL PATRON STATE
    @Operation(summary = "Cancelar una venta (cambia a estado CANCELADA)")
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<Venta>> cancelarVenta(@PathVariable Integer id) {
        log.info("PUT /ventas/{}/cancelar - Cancelando venta", id);
        Venta venta = ventaServiceImpl.cancelarVenta(id);
        return ResponseEntity.ok(ApiResponse.success(venta, "Venta cancelada exitosamente"));
    }
}