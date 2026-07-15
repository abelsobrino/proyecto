package pe.edu.utp.proyecto.controller.comprobantes;

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
import pe.edu.utp.proyecto.service.comprobantes.FacturaService;
import pe.edu.utp.proyecto.service.patron.estructural_decorator.ComprobanteConPDF;
import pe.edu.utp.proyecto.service.patron.estructural_decorator.ComprobanteConQR;

import java.util.List;

@RestController
@RequestMapping("/comprobantes/facturas")
@Tag(name = "Facturas", description = "Gestion de facturas")
@RequiredArgsConstructor
@Slf4j
public class FacturaController {

    private final FacturaService facturaService;

    /** Registra una nueva factura */
    @Operation(summary = "Crear una nueva factura")
    @PostMapping
    public ResponseEntity<ApiResponse<Factura>> crearFactura(@Valid @RequestBody Factura factura) {
        log.info("POST /comprobantes/facturas - Creando nueva factura");
        Factura creada = facturaService.guardarFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Factura creada exitosamente"));
    }

    /** Busca una factura por su ID */
    @Operation(summary = "Obtener una factura por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Factura>> obtenerFactura(@PathVariable Long id) {
        log.info("GET /comprobantes/facturas/{} - Obteniendo factura", id);
        return facturaService.obtenerFacturaPorId(id)
                .map(factura -> ResponseEntity.ok(ApiResponse.success(factura)))
                .orElse(ResponseEntity.notFound().build());
    }

    /** Lista todas las facturas */
    @Operation(summary = "Obtener todas las facturas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Factura>>> obtenerTodasFacturas() {
        log.info("GET /comprobantes/facturas - Obteniendo todas las facturas");
        List<Factura> facturas = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok(ApiResponse.success(facturas));
    }

    /** Actualiza una factura existente */
    @Operation(summary = "Actualizar una factura existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Factura>> actualizarFactura(
            @PathVariable Long id,
            @Valid @RequestBody Factura factura) {
        log.info("PUT /comprobantes/facturas/{} - Actualizando factura", id);
        Factura actualizada = facturaService.actualizarFactura(id, factura);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Factura actualizada exitosamente"));
    }

    /** Elimina una factura por su ID */
    @Operation(summary = "Eliminar una factura por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarFactura(@PathVariable Long id) {
        log.info("DELETE /comprobantes/facturas/{} - Eliminando factura", id);
        facturaService.eliminarFactura(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Factura eliminada exitosamente"));
    }

    // =============================================
    // CONSULTAS ESPECIALIZADAS
    // =============================================

    /** Busca factura por RUC exacto */
    @Operation(summary = "Buscar factura por RUC del cliente")
    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<ApiResponse<Factura>> buscarPorRuc(@PathVariable String ruc) {
        log.info("GET /comprobantes/facturas/ruc/{} - Buscando por RUC", ruc);
        Factura factura = facturaService.buscarPorRucCliente(ruc);
        if (factura != null) {
            return ResponseEntity.ok(ApiResponse.success(factura));
        }
        return ResponseEntity.notFound().build();
    }

    /** Busca facturas cuyo RUC contenga un texto */
    @Operation(summary = "Buscar facturas por RUC que contenga")
    @GetMapping("/ruc/contiene/{ruc}")
    public ResponseEntity<ApiResponse<List<Factura>>> buscarPorRucContaining(@PathVariable String ruc) {
        log.info("GET /comprobantes/facturas/ruc/contiene/{} - Buscando por RUC que contenga", ruc);
        List<Factura> facturas = facturaService.buscarPorRucClienteContaining(ruc);
        return ResponseEntity.ok(ApiResponse.success(facturas));
    }

    /** Busca facturas con total mayor a un valor */
    @Operation(summary = "Buscar facturas con total mayor a")
    @GetMapping("/total-mayor/{total}")
    public ResponseEntity<ApiResponse<List<Factura>>> buscarPorTotalMayor(@PathVariable double total) {
        log.info("GET /comprobantes/facturas/total-mayor/{} - Buscando por total", total);
        List<Factura> facturas = facturaService.buscarPorTotalMayor(total);
        return ResponseEntity.ok(ApiResponse.success(facturas));
    }

    // =============================================
    // PATRON DECORATOR
    // =============================================

    /** Emite una factura agregando generacion de codigo QR */
    @Operation(summary = "Emitir factura con codigo QR")
    @PostMapping("/emitir-con-qr")
    public ResponseEntity<ApiResponse<String>> emitirFacturaConQR(@Valid @RequestBody Factura factura) {
        log.info("POST /comprobantes/facturas/emitir-con-qr - Emitiendo factura con QR");
        ComprobanteConQR facturaConQR = new ComprobanteConQR(factura);
        facturaConQR.emitir();
        facturaService.guardarFactura(factura);
        return ResponseEntity.ok(ApiResponse.success("Factura emitida con QR", "Serie: " + factura.getSerie()));
    }

    /** Emite una factura agregando exportacion a PDF */
    @Operation(summary = "Emitir factura con exportacion a PDF")
    @PostMapping("/emitir-con-pdf")
    public ResponseEntity<ApiResponse<String>> emitirFacturaConPDF(@Valid @RequestBody Factura factura) {
        log.info("POST /comprobantes/facturas/emitir-con-pdf - Emitiendo factura con PDF");
        ComprobanteConPDF facturaConPDF = new ComprobanteConPDF(factura);
        facturaConPDF.emitir();
        facturaService.guardarFactura(factura);
        return ResponseEntity.ok(ApiResponse.success("Factura emitida con PDF", "Serie: " + factura.getSerie()));
    }
}