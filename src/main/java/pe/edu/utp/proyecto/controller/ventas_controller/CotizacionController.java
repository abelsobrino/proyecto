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
import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;
import pe.edu.utp.proyecto.service.patron.singleton.ConfiguracionGlobal;
import pe.edu.utp.proyecto.service.ventas_service.CotizacionService;

import java.util.List;

@RestController
@RequestMapping("/ventas/cotizaciones")
@Tag(name = "Cotizaciones", description = "Gestion de cotizaciones")
@RequiredArgsConstructor
@Slf4j
public class CotizacionController {

    private final CotizacionService cotizacionService;

    @Operation(summary = "Crear una nueva cotizacion")
    @PostMapping
    public ResponseEntity<ApiResponse<Cotizacion>> crearCotizacion(@Valid @RequestBody Cotizacion cotizacion) {
        log.info("POST /ventas/cotizaciones - Creando nueva cotizacion");

        // USO DEL PATRON SINGLETON (también visible en el service)
        Cotizacion creada = cotizacionService.guardarCotizacion(cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Cotizacion creada exitosamente"));
    }

    // USO DEL PATRON SINGLETON (directo en controller)
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

    @Operation(summary = "Obtener una cotizacion por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cotizacion>> obtenerCotizacion(@PathVariable Integer id) {
        log.info("GET /ventas/cotizaciones/{} - Obteniendo cotizacion", id);
        return cotizacionService.obtenerCotizacionPorId(id)
                .map(cotizacion -> ResponseEntity.ok(ApiResponse.success(cotizacion)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las cotizaciones")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Cotizacion>>> obtenerTodasCotizaciones() {
        log.info("GET /ventas/cotizaciones - Obteniendo todas las cotizaciones");
        List<Cotizacion> cotizaciones = cotizacionService.obtenerTodasLasCotizaciones();
        return ResponseEntity.ok(ApiResponse.success(cotizaciones));
    }

    @Operation(summary = "Actualizar una cotizacion existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cotizacion>> actualizarCotizacion(
            @PathVariable Integer id,
            @Valid @RequestBody Cotizacion cotizacion) {
        log.info("PUT /ventas/cotizaciones/{} - Actualizando cotizacion", id);
        Cotizacion actualizada = cotizacionService.actualizarCotizacion(id, cotizacion);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Cotizacion actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una cotizacion por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCotizacion(@PathVariable Integer id) {
        log.info("DELETE /ventas/cotizaciones/{} - Eliminando cotizacion", id);
        cotizacionService.eliminarCotizacion(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Cotizacion eliminada exitosamente"));
    }

    @Operation(summary = "Obtener ultimas 5 cotizaciones")
    @GetMapping("/ultimas")
    public ResponseEntity<ApiResponse<List<Cotizacion>>> obtenerUltimas() {
        log.info("GET /ventas/cotizaciones/ultimas - Obteniendo ultimas 5 cotizaciones");
        List<Cotizacion> cotizaciones = cotizacionService.obtenerUltimas5Cotizaciones();
        return ResponseEntity.ok(ApiResponse.success(cotizaciones));
    }
}