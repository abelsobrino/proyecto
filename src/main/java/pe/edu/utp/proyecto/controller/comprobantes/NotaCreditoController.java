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

/**
 * Controlador REST para la gestion de notas de credito.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/comprobantes/notas-credito")
@Tag(name = "Notas de Credito", description = "Gestion de notas de credito")
@RequiredArgsConstructor
@Slf4j
public class NotaCreditoController {

    private final NotaCreditoService notaCreditoService;

    /**
     * Crea una nueva nota de credito en el sistema.
     * @param notaCredito Datos de la nota de credito a crear.
     * @return Nota de credito creada con su ID generado.
     */
    @Operation(summary = "Crear una nueva nota de credito")
    @PostMapping
    public ResponseEntity<ApiResponse<NotaCredito>> crearNotaCredito(@Valid @RequestBody NotaCredito notaCredito) {
        log.info("POST /comprobantes/notas-credito - Creando nueva nota de credito");
        NotaCredito creada = notaCreditoService.guardarNotaCredito(notaCredito);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Nota de credito creada exitosamente"));
    }

    /**
     * Obtiene una nota de credito por su ID.
     * @param id ID de la nota de credito.
     * @return Nota de credito encontrada o 404 Not Found.
     */
    @Operation(summary = "Obtener una nota de credito por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotaCredito>> obtenerNotaCredito(@PathVariable Long id) {
        log.info("GET /comprobantes/notas-credito/{} - Obteniendo nota de credito", id);
        return notaCreditoService.obtenerNotaCreditoPorId(id)
                .map(nota -> ResponseEntity.ok(ApiResponse.success(nota)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todas las notas de credito registradas.
     * @return Lista de notas de credito.
     */
    @Operation(summary = "Obtener todas las notas de credito")
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotaCredito>>> obtenerTodasNotasCredito() {
        log.info("GET /comprobantes/notas-credito - Obteniendo todas las notas de credito");
        List<NotaCredito> notas = notaCreditoService.obtenerTodasLasNotasCredito();
        return ResponseEntity.ok(ApiResponse.success(notas));
    }

    /**
     * Actualiza una nota de credito existente.
     * @param id ID de la nota de credito a actualizar.
     * @param notaCredito Datos actualizados.
     * @return Nota de credito actualizada.
     */
    @Operation(summary = "Actualizar una nota de credito existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NotaCredito>> actualizarNotaCredito(
            @PathVariable Long id,
            @Valid @RequestBody NotaCredito notaCredito) {
        log.info("PUT /comprobantes/notas-credito/{} - Actualizando nota de credito", id);
        NotaCredito actualizada = notaCreditoService.actualizarNotaCredito(id, notaCredito);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Nota de credito actualizada exitosamente"));
    }

    /**
     * Elimina una nota de credito por su ID.
     * @param id ID de la nota de credito a eliminar.
     */
    @Operation(summary = "Eliminar una nota de credito por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarNotaCredito(@PathVariable Long id) {
        log.info("DELETE /comprobantes/notas-credito/{} - Eliminando nota de credito", id);
        notaCreditoService.eliminarNotaCredito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Nota de credito eliminada exitosamente"));
    }

    /**
     * Busca notas de credito por motivo.
     * @param motivo Texto a buscar en el motivo.
     * @return Lista de notas de credito.
     */
    @Operation(summary = "Buscar notas de credito por motivo")
    @GetMapping("/motivo/{motivo}")
    public ResponseEntity<ApiResponse<List<NotaCredito>>> buscarPorMotivo(@PathVariable String motivo) {
        log.info("GET /comprobantes/notas-credito/motivo/{} - Buscando por motivo", motivo);
        List<NotaCredito> notas = notaCreditoService.buscarPorMotivo(motivo);
        return ResponseEntity.ok(ApiResponse.success(notas));
    }

    /**
     * Busca notas de credito con total mayor al especificado.
     * @param total Valor minimo del total.
     * @return Lista de notas de credito.
     */
    @Operation(summary = "Buscar notas de credito con total mayor a")
    @GetMapping("/total-mayor/{total}")
    public ResponseEntity<ApiResponse<List<NotaCredito>>> buscarPorTotalMayor(@PathVariable double total) {
        log.info("GET /comprobantes/notas-credito/total-mayor/{} - Buscando por total", total);
        List<NotaCredito> notas = notaCreditoService.buscarPorTotalMayor(total);
        return ResponseEntity.ok(ApiResponse.success(notas));
    }
}