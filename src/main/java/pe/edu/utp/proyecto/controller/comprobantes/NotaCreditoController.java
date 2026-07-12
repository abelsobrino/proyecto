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
import java.util.List;

@RestController
@RequestMapping("/comprobantes/notas-credito")
@Tag(name = "Notas de Credito", description = "Gestion de notas de credito")
@RequiredArgsConstructor
@Slf4j
public class NotaCreditoController {

    private final NotaCreditoService notaCreditoService;

    @Operation(summary = "Crear una nueva nota de credito")
    @PostMapping
    public ResponseEntity<ApiResponse<NotaCredito>> crearNotaCredito(@Valid @RequestBody NotaCredito notaCredito) {
        log.info("POST /comprobantes/notas-credito - Creando nueva nota de credito");
        NotaCredito creada = notaCreditoService.guardarNotaCredito(notaCredito);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Nota de credito creada exitosamente"));
    }

    @Operation(summary = "Obtener una nota de credito por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotaCredito>> obtenerNotaCredito(@PathVariable Long id) {
        log.info("GET /comprobantes/notas-credito/{} - Obteniendo nota de credito", id);
        return notaCreditoService.obtenerNotaCreditoPorId(id)
                .map(nota -> ResponseEntity.ok(ApiResponse.success(nota)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las notas de credito")
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotaCredito>>> obtenerTodasNotasCredito() {
        log.info("GET /comprobantes/notas-credito - Obteniendo todas las notas de credito");
        List<NotaCredito> notas = notaCreditoService.obtenerTodasLasNotasCredito();
        return ResponseEntity.ok(ApiResponse.success(notas));
    }

    @Operation(summary = "Actualizar una nota de credito existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NotaCredito>> actualizarNotaCredito(
            @PathVariable Long id,
            @Valid @RequestBody NotaCredito notaCredito) {
        log.info("PUT /comprobantes/notas-credito/{} - Actualizando nota de credito", id);
        NotaCredito actualizada = notaCreditoService.actualizarNotaCredito(id, notaCredito);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Nota de credito actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una nota de credito por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarNotaCredito(@PathVariable Long id) {
        log.info("DELETE /comprobantes/notas-credito/{} - Eliminando nota de credito", id);
        notaCreditoService.eliminarNotaCredito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Nota de credito eliminada exitosamente"));
    }
}