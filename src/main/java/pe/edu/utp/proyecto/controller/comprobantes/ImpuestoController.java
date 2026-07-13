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
import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;
import pe.edu.utp.proyecto.service.comprobantes.ImpuestoService;

import java.util.List;

/**
 * Controlador REST para la gestion de impuestos.
 * Expone endpoints para operaciones CRUD de impuestos.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/comprobantes/impuestos")
@Tag(name = "Impuestos", description = "Gestion de impuestos (IGV, ISC, etc.)")
@RequiredArgsConstructor
@Slf4j
public class ImpuestoController {

    private final ImpuestoService impuestoService;

    // =============================================
    // CRUD
    // =============================================

    /**
     * Crea un nuevo impuesto en el sistema.
     * @param impuesto Datos del impuesto a crear.
     * @return Impuesto creado.
     */
    @Operation(summary = "Crear un nuevo impuesto")
    @PostMapping
    public ResponseEntity<ApiResponse<Impuesto>> crearImpuesto(@Valid @RequestBody Impuesto impuesto) {
        log.info("POST /comprobantes/impuestos - Creando nuevo impuesto: {}", impuesto.getTipoImpuesto());
        Impuesto creado = impuestoService.guardarImpuesto(impuesto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Impuesto creado exitosamente"));
    }

    /**
     * Obtiene un impuesto por su tipo.
     * @param tipo Tipo del impuesto (IGV, ISC, etc.).
     * @return Impuesto encontrado o 404 Not Found.
     */
    @Operation(summary = "Obtener un impuesto por su tipo")
    @GetMapping("/{tipo}")
    public ResponseEntity<ApiResponse<Impuesto>> obtenerImpuesto(@PathVariable String tipo) {
        log.info("GET /comprobantes/impuestos/{} - Obteniendo impuesto", tipo);
        return impuestoService.obtenerImpuestoPorId(tipo)
                .map(impuesto -> ResponseEntity.ok(ApiResponse.success(impuesto)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los impuestos registrados.
     * @return Lista de impuestos.
     */
    @Operation(summary = "Obtener todos los impuestos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Impuesto>>> obtenerTodosImpuestos() {
        log.info("GET /comprobantes/impuestos - Obteniendo todos los impuestos");
        List<Impuesto> impuestos = impuestoService.obtenerTodosLosImpuestos();
        return ResponseEntity.ok(ApiResponse.success(impuestos));
    }

    /**
     * Actualiza un impuesto existente.
     * @param tipo Tipo del impuesto a actualizar.
     * @param impuesto Datos actualizados.
     * @return Impuesto actualizado.
     */
    @Operation(summary = "Actualizar un impuesto existente")
    @PutMapping("/{tipo}")
    public ResponseEntity<ApiResponse<Impuesto>> actualizarImpuesto(
            @PathVariable String tipo,
            @Valid @RequestBody Impuesto impuesto) {
        log.info("PUT /comprobantes/impuestos/{} - Actualizando impuesto", tipo);
        Impuesto actualizado = impuestoService.actualizarImpuesto(tipo, impuesto);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Impuesto actualizado exitosamente"));
    }

    /**
     * Elimina un impuesto por su tipo.
     * @param tipo Tipo del impuesto a eliminar.
     */
    @Operation(summary = "Eliminar un impuesto por su tipo")
    @DeleteMapping("/{tipo}")
    public ResponseEntity<ApiResponse<Void>> eliminarImpuesto(@PathVariable String tipo) {
        log.info("DELETE /comprobantes/impuestos/{} - Eliminando impuesto", tipo);
        impuestoService.eliminarImpuesto(tipo);
        return ResponseEntity.ok(ApiResponse.success(null, "Impuesto eliminado exitosamente"));
    }
}