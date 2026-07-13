package pe.edu.utp.proyecto.controller.finanzas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;
import pe.edu.utp.proyecto.service.finanzas.MovimientoFinancieroService;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para la gestion de movimientos financieros.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/finanzas/movimientos")
@Tag(name = "Movimientos Financieros", description = "Gestion de movimientos financieros")
@RequiredArgsConstructor
@Slf4j
public class MovimientoFinancieroController {

    private final MovimientoFinancieroService movimientoService;

    /**
     * Registra un nuevo movimiento financiero.
     * @param movimiento Datos del movimiento a registrar.
     * @return Movimiento registrado.
     */
    @Operation(summary = "Registrar un nuevo movimiento financiero")
    @PostMapping
    public ResponseEntity<ApiResponse<MovimientoFinanciero>> crearMovimiento(@Valid @RequestBody MovimientoFinanciero movimiento) {
        log.info("POST /finanzas/movimientos - Registrando nuevo movimiento");
        MovimientoFinanciero creado = movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Movimiento registrado exitosamente"));
    }

    /**
     * Obtiene un movimiento financiero por su ID.
     * @param id ID del movimiento.
     * @return Movimiento encontrado o 404 Not Found.
     */
    @Operation(summary = "Obtener un movimiento por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoFinanciero>> obtenerMovimiento(@PathVariable Integer id) {
        log.info("GET /finanzas/movimientos/{} - Obteniendo movimiento", id);
        return movimientoService.obtenerMovimientoPorId(id)
                .map(movimiento -> ResponseEntity.ok(ApiResponse.success(movimiento)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los movimientos financieros.
     * @return Lista de movimientos.
     */
    @Operation(summary = "Obtener todos los movimientos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MovimientoFinanciero>>> obtenerTodosMovimientos() {
        log.info("GET /finanzas/movimientos - Obteniendo todos los movimientos");
        List<MovimientoFinanciero> movimientos = movimientoService.obtenerTodosLosMovimientos();
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    /**
     * Actualiza un movimiento financiero existente.
     * @param id ID del movimiento a actualizar.
     * @param movimiento Datos actualizados.
     * @return Movimiento actualizado.
     */
    @Operation(summary = "Actualizar un movimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoFinanciero>> actualizarMovimiento(
            @PathVariable Integer id,
            @Valid @RequestBody MovimientoFinanciero movimiento) {
        log.info("PUT /finanzas/movimientos/{} - Actualizando movimiento", id);
        MovimientoFinanciero actualizado = movimientoService.actualizarMovimiento(id, movimiento);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Movimiento actualizado exitosamente"));
    }

    /**
     * Elimina un movimiento financiero por su ID.
     * @param id ID del movimiento a eliminar.
     */
    @Operation(summary = "Eliminar un movimiento por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarMovimiento(@PathVariable Integer id) {
        log.info("DELETE /finanzas/movimientos/{} - Eliminando movimiento", id);
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Movimiento eliminado exitosamente"));
    }

    /**
     * Busca movimientos financieros por tipo (INGRESO/EGRESO).
     * @param tipo Tipo de movimiento.
     * @return Lista de movimientos del tipo especificado.
     */
    @Operation(summary = "Buscar movimientos por tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ApiResponse<List<MovimientoFinanciero>>> buscarPorTipo(@PathVariable String tipo) {
        log.info("GET /finanzas/movimientos/tipo/{} - Buscando por tipo", tipo);
        List<MovimientoFinanciero> movimientos = movimientoService.obtenerMovimientosPorTipo(tipo);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    /**
     * Busca movimientos financieros por fecha.
     * @param fecha Fecha del movimiento.
     * @return Lista de movimientos de esa fecha.
     */
    @Operation(summary = "Buscar movimientos por fecha")
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponse<List<MovimientoFinanciero>>> buscarPorFecha(@PathVariable LocalDate fecha) {
        log.info("GET /finanzas/movimientos/fecha/{} - Buscando por fecha", fecha);
        List<MovimientoFinanciero> movimientos = movimientoService.obtenerMovimientosPorFecha(fecha);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }
}