package pe.edu.utp.proyecto.controller.comprobantes_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.comprobantes.Factura;
import pe.edu.utp.proyecto.service.comprobantes_service.FacturaService;
import java.util.List;

@RestController
@RequestMapping("/comprobantes/facturas")
@Tag(name = "Facturas", description = "Gestion de facturas")
@RequiredArgsConstructor
@Slf4j
public class FacturaController {

    private final FacturaService facturaService;

    @Operation(summary = "Crear una nueva factura")
    @PostMapping
    public ResponseEntity<ApiResponse<Factura>> crearFactura(@Valid @RequestBody Factura factura) {
        log.info("POST /comprobantes/facturas - Creando nueva factura");
        Factura creada = facturaService.guardarFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Factura creada exitosamente"));
    }

    @Operation(summary = "Obtener una factura por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Factura>> obtenerFactura(@PathVariable Long id) {
        log.info("GET /comprobantes/facturas/{} - Obteniendo factura", id);
        return facturaService.obtenerFacturaPorId(id)
                .map(factura -> ResponseEntity.ok(ApiResponse.success(factura)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las facturas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Factura>>> obtenerTodasFacturas() {
        log.info("GET /comprobantes/facturas - Obteniendo todas las facturas");
        List<Factura> facturas = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(ApiResponse.success(facturas));
    }

    @Operation(summary = "Actualizar una factura existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Factura>> actualizarFactura(
            @PathVariable Long id,
            @Valid @RequestBody Factura factura) {
        log.info("PUT /comprobantes/facturas/{} - Actualizando factura", id);
        Factura actualizada = facturaService.actualizarFactura(id, factura);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Factura actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una factura por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarFactura(@PathVariable Long id) {
        log.info("DELETE /comprobantes/facturas/{} - Eliminando factura", id);
        facturaService.eliminarFactura(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Factura eliminada exitosamente"));
    }
}