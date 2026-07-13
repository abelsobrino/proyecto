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
import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;
import pe.edu.utp.proyecto.service.patron.singleton.ConfiguracionGlobal;
import pe.edu.utp.proyecto.service.ventas.CotizacionService;

import java.util.Date;
import java.util.List;

/**
 * Controlador REST para la gestion de cotizaciones.
 */
@RestController
@RequestMapping("/ventas/cotizaciones")
@Tag(name = "Cotizaciones", description = "Gestion de cotizaciones")
@RequiredArgsConstructor
@Slf4j
public class CotizacionController {

    private final CotizacionService cotizacionService;

    /**
     * Crea una nueva cotizacion.
     * @param cotizacion Datos de la cotizacion.
     * @return Cotizacion creada.
     */
    @Operation(summary = "Crear una nueva cotizacion")
    @PostMapping
    public ResponseEntity<ApiResponse<Cotizacion>> crearCotizacion(@Valid @RequestBody Cotizacion cotizacion) {
        log.info("POST /ventas/cotizaciones - Creando nueva cotizacion");
        Cotizacion creada = cotizacionService.guardarCotizacion(cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Cotizacion creada exitosamente"));
    }

    /**
     * Obtiene configuracion del sistema (Singleton).
     * @return Configuracion del sistema.
     */
    @Operation(summary = "Obtener configuracion del sistema")
    @GetMapping("/configuracion")
    public ResponseEntity<ApiResponse<String>> obtenerConfiguracion() {
        log.info("GET /ventas/cotizaciones/configuracion - Obteniendo configuracion");
        ConfiguracionGlobal config = ConfiguracionGlobal.getInstance();
        String info = "Sistema: " + config.getNombreSistema() +
                ", Version: " + config.getVersion() +
                ", Entorno: " + config.getEntorno();
        return ResponseEntity.ok(ApiResponse.success(info, "Configuracion obtenida exitosamente"));
    }

    /**
     * Obtiene una cotizacion por su ID.
     * @param id ID de la cotizacion.
     * @return Cotizacion encontrada o 404.
     */
    @Operation(summary = "Obtener una cotizacion por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cotizacion>> obtenerCotizacion(@PathVariable Integer id) {
        log.info("GET /ventas/cotizaciones/{} - Obteniendo cotizacion", id);
        return cotizacionService.obtenerCotizacionPorId(id)
                .map(cotizacion -> ResponseEntity.ok(ApiResponse.success(cotizacion)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todas las cotizaciones.
     * @return Lista de cotizaciones.
     */
    @Operation(summary = "Obtener todas las cotizaciones")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Cotizacion>>> obtenerTodasCotizaciones() {
        log.info("GET /ventas/cotizaciones - Obteniendo todas las cotizaciones");
        List<Cotizacion> cotizaciones = cotizacionService.obtenerTodasLasCotizaciones();
        return ResponseEntity.ok(ApiResponse.success(cotizaciones));
    }

    /**
     * Actualiza una cotizacion existente.
     * @param id ID de la cotizacion.
     * @param cotizacion Datos actualizados.
     * @return Cotizacion actualizada.
     */
    @Operation(summary = "Actualizar una cotizacion existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cotizacion>> actualizarCotizacion(
            @PathVariable Integer id,
            @Valid @RequestBody Cotizacion cotizacion) {
        log.info("PUT /ventas/cotizaciones/{} - Actualizando cotizacion", id);
        Cotizacion actualizada = cotizacionService.actualizarCotizacion(id, cotizacion);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Cotizacion actualizada exitosamente"));
    }

    /**
     * Elimina una cotizacion por su ID.
     * @param id ID de la cotizacion a eliminar.
     */
    @Operation(summary = "Eliminar una cotizacion por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCotizacion(@PathVariable Integer id) {
        log.info("DELETE /ventas/cotizaciones/{} - Eliminando cotizacion", id);
        cotizacionService.eliminarCotizacion(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Cotizacion eliminada exitosamente"));
    }

    /**
     * Busca cotizaciones entre dos fechas.
     * @param inicio Fecha de inicio.
     * @param fin Fecha de fin.
     * @return Lista de cotizaciones en el rango.
     */
    @Operation(summary = "Buscar cotizaciones entre fechas")
    @GetMapping("/entre-fechas")
    public ResponseEntity<ApiResponse<List<Cotizacion>>> obtenerCotizacionesEntreFechas(
            @RequestParam Date inicio,
            @RequestParam Date fin) {
        log.info("GET /ventas/cotizaciones/entre-fechas - Buscando cotizaciones entre {} y {}", inicio, fin);
        List<Cotizacion> cotizaciones = cotizacionService.obtenerCotizacionesEntreFechas(inicio, fin);
        return ResponseEntity.ok(ApiResponse.success(cotizaciones));
    }

    /**
     * Busca cotizaciones cuyo total estimado sea mayor a un valor.
     * @param total Valor minimo del total estimado.
     * @return Lista de cotizaciones.
     */
    @Operation(summary = "Buscar cotizaciones por total mayor a")
    @GetMapping("/total-mayor/{total}")
    public ResponseEntity<ApiResponse<List<Cotizacion>>> obtenerCotizacionesPorTotalMayor(@PathVariable Double total) {
        log.info("GET /ventas/cotizaciones/total-mayor/{} - Buscando cotizaciones con total mayor", total);
        List<Cotizacion> cotizaciones = cotizacionService.obtenerCotizacionesPorTotalMayor(total);
        return ResponseEntity.ok(ApiResponse.success(cotizaciones));
    }

    /**
     * Obtiene las 5 cotizaciones mas recientes.
     * @return Lista de las 5 cotizaciones mas recientes.
     */
    @Operation(summary = "Obtener ultimas 5 cotizaciones")
    @GetMapping("/ultimas")
    public ResponseEntity<ApiResponse<List<Cotizacion>>> obtenerUltimas() {
        log.info("GET /ventas/cotizaciones/ultimas - Obteniendo ultimas 5 cotizaciones");
        List<Cotizacion> cotizaciones = cotizacionService.obtenerUltimas5Cotizaciones();
        return ResponseEntity.ok(ApiResponse.success(cotizaciones));
    }
}