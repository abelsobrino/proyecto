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
import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;
import pe.edu.utp.proyecto.service.comprobantes.NotaCreditoService;
import pe.edu.utp.proyecto.service.patron.estructural_decorator.ComprobanteConPDF;
import pe.edu.utp.proyecto.service.patron.estructural_decorator.ComprobanteConQR;

import java.util.List;

@RestController
@RequestMapping("/comprobantes/notas-credito")
@Tag(name = "Notas de Credito", description = "Gestion de notas de credito")
@RequiredArgsConstructor
@Slf4j
public class NotaCreditoController {

    private final NotaCreditoService notaCreditoService;

    /** Registra una nueva nota de credito */
    @Operation(summary = "Crear una nueva nota de credito")
    @PostMapping
    public ResponseEntity<ApiResponse<NotaCredito>> crearNotaCredito(@Valid @RequestBody NotaCredito notaCredito) {
        log.info("POST /comprobantes/notas-credito - Creando nueva nota de credito");
        NotaCredito creada = notaCreditoService.guardarNotaCredito(notaCredito);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Nota de credito creada exitosamente"));
    }

    /** Busca una nota de credito por su ID */
    @Operation(summary = "Obtener una nota de credito por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotaCredito>> obtenerNotaCredito(@PathVariable Long id) {
        log.info("GET /comprobantes/notas-credito/{} - Obteniendo nota de credito", id);
        return notaCreditoService.obtenerNotaCreditoPorId(id)
                .map(nota -> ResponseEntity.ok(ApiResponse.success(nota)))
                .orElse(ResponseEntity.notFound().build());
    }

    /** Lista todas las notas de credito */
    @Operation(summary = "Obtener todas las notas de credito")
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotaCredito>>> obtenerTodasNotasCredito() {
        log.info("GET /comprobantes/notas-credito - Obteniendo todas las notas de credito");
        List<NotaCredito> notas = notaCreditoService.obtenerTodasLasNotasCredito();
        return ResponseEntity.ok(ApiResponse.success(notas));
    }

    /** Actualiza una nota de credito existente */
    @Operation(summary = "Actualizar una nota de credito existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NotaCredito>> actualizarNotaCredito(
            @PathVariable Long id,
            @Valid @RequestBody NotaCredito notaCredito) {
        log.info("PUT /comprobantes/notas-credito/{} - Actualizando nota de credito", id);
        NotaCredito actualizada = notaCreditoService.actualizarNotaCredito(id, notaCredito);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Nota de credito actualizada exitosamente"));
    }

    /** Elimina una nota de credito por su ID */
    @Operation(summary = "Eliminar una nota de credito por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarNotaCredito(@PathVariable Long id) {
        log.info("DELETE /comprobantes/notas-credito/{} - Eliminando nota de credito", id);
        notaCreditoService.eliminarNotaCredito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Nota de credito eliminada exitosamente"));
    }

    // =============================================
    // CONSULTAS ESPECIALIZADAS
    // =============================================

    /** Busca notas de credito por motivo */
    @Operation(summary = "Buscar notas de credito por motivo")
    @GetMapping("/motivo/{motivo}")
    public ResponseEntity<ApiResponse<List<NotaCredito>>> buscarPorMotivo(@PathVariable String motivo) {
        log.info("GET /comprobantes/notas-credito/motivo/{} - Buscando por motivo", motivo);
        List<NotaCredito> notas = notaCreditoService.buscarPorMotivo(motivo);
        return ResponseEntity.ok(ApiResponse.success(notas));
    }

    /** Busca notas de credito con total mayor a un valor */
    @Operation(summary = "Buscar notas de credito con total mayor a")
    @GetMapping("/total-mayor/{total}")
    public ResponseEntity<ApiResponse<List<NotaCredito>>> buscarPorTotalMayor(@PathVariable double total) {
        log.info("GET /comprobantes/notas-credito/total-mayor/{} - Buscando por total", total);
        List<NotaCredito> notas = notaCreditoService.buscarPorTotalMayor(total);
        return ResponseEntity.ok(ApiResponse.success(notas));
    }

    // =============================================
    // PATRON DECORATOR
    // =============================================

    /** Emite una nota de credito agregando generacion de codigo QR */
    @Operation(summary = "Emitir nota de credito con codigo QR")
    @PostMapping("/emitir-con-qr")
    public ResponseEntity<ApiResponse<String>> emitirNotaCreditoConQR(@Valid @RequestBody NotaCredito nota) {
        log.info("POST /comprobantes/notas-credito/emitir-con-qr - Emitiendo nota de credito con QR");
        ComprobanteConQR notaConQR = new ComprobanteConQR(nota);
        notaConQR.emitir();
        notaCreditoService.guardarNotaCredito(nota);
        return ResponseEntity.ok(ApiResponse.success("Nota de credito emitida con QR", "Serie: " + nota.getSerie()));
    }

    /** Emite una nota de credito agregando exportacion a PDF */
    @Operation(summary = "Emitir nota de credito con exportacion a PDF")
    @PostMapping("/emitir-con-pdf")
    public ResponseEntity<ApiResponse<String>> emitirNotaCreditoConPDF(@Valid @RequestBody NotaCredito nota) {
        log.info("POST /comprobantes/notas-credito/emitir-con-pdf - Emitiendo nota de credito con PDF");
        ComprobanteConPDF notaConPDF = new ComprobanteConPDF(nota);
        notaConPDF.emitir();
        notaCreditoService.guardarNotaCredito(nota);
        return ResponseEntity.ok(ApiResponse.success("Nota de credito emitida con PDF", "Serie: " + nota.getSerie()));
    }
}