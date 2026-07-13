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
import pe.edu.utp.proyecto.modelo.finanzas.Reporte;
import pe.edu.utp.proyecto.service.finanzas.ReporteService;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para la gestion de reportes.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/finanzas/reportes")
@Tag(name = "Reportes", description = "Gestion de reportes financieros y comerciales")
@RequiredArgsConstructor
@Slf4j
public class ReporteController {

    private final ReporteService reporteService;

    /**
     * Genera un nuevo reporte.
     * @param reporte Datos del reporte a generar.
     * @return Reporte generado.
     */
    @Operation(summary = "Generar un nuevo reporte")
    @PostMapping
    public ResponseEntity<ApiResponse<Reporte>> generarReporte(@Valid @RequestBody Reporte reporte) {
        log.info("POST /finanzas/reportes - Generando nuevo reporte");
        Reporte creado = reporteService.guardarReporte(reporte);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Reporte generado exitosamente"));
    }

    /**
     * Obtiene un reporte por su ID.
     * @param id ID del reporte.
     * @return Reporte encontrado o 404 Not Found.
     */
    @Operation(summary = "Obtener un reporte por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Reporte>> obtenerReporte(@PathVariable Integer id) {
        log.info("GET /finanzas/reportes/{} - Obteniendo reporte", id);
        return reporteService.obtenerReportePorId(id)
                .map(reporte -> ResponseEntity.ok(ApiResponse.success(reporte)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los reportes generados.
     * @return Lista de reportes.
     */
    @Operation(summary = "Obtener todos los reportes")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Reporte>>> obtenerTodosReportes() {
        log.info("GET /finanzas/reportes - Obteniendo todos los reportes");
        List<Reporte> reportes = reporteService.obtenerTodosLosReportes();
        return ResponseEntity.ok(ApiResponse.success(reportes));
    }

    /**
     * Actualiza un reporte existente.
     * @param id ID del reporte a actualizar.
     * @param reporte Datos actualizados.
     * @return Reporte actualizado.
     */
    @Operation(summary = "Actualizar un reporte existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Reporte>> actualizarReporte(
            @PathVariable Integer id,
            @Valid @RequestBody Reporte reporte) {
        log.info("PUT /finanzas/reportes/{} - Actualizando reporte", id);
        Reporte actualizado = reporteService.actualizarReporte(id, reporte);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Reporte actualizado exitosamente"));
    }

    /**
     * Elimina un reporte por su ID.
     * @param id ID del reporte a eliminar.
     */
    @Operation(summary = "Eliminar un reporte por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarReporte(@PathVariable Integer id) {
        log.info("DELETE /finanzas/reportes/{} - Eliminando reporte", id);
        reporteService.eliminarReporte(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Reporte eliminado exitosamente"));
    }

    /**
     * Busca reportes por tipo.
     * @param tipoReporte Tipo de reporte (VENTAS, COMPRAS, FINANCIERO, etc.).
     * @return Lista de reportes del tipo especificado.
     */
    @Operation(summary = "Buscar reportes por tipo")
    @GetMapping("/tipo/{tipoReporte}")
    public ResponseEntity<ApiResponse<List<Reporte>>> buscarPorTipo(@PathVariable String tipoReporte) {
        log.info("GET /finanzas/reportes/tipo/{} - Buscando por tipo", tipoReporte);
        List<Reporte> reportes = reporteService.obtenerReportesPorTipo(tipoReporte);
        return ResponseEntity.ok(ApiResponse.success(reportes));
    }

    /**
     * Busca reportes por fecha de generacion.
     * @param fecha Fecha de generacion del reporte.
     * @return Lista de reportes de esa fecha.
     */
    @Operation(summary = "Buscar reportes por fecha de generacion")
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponse<List<Reporte>>> buscarPorFecha(@PathVariable LocalDate fecha) {
        log.info("GET /finanzas/reportes/fecha/{} - Buscando por fecha", fecha);
        List<Reporte> reportes = reporteService.obtenerReportesPorFecha(fecha);
        return ResponseEntity.ok(ApiResponse.success(reportes));
    }
}