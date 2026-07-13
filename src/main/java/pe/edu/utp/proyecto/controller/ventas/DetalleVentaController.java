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
import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;
import pe.edu.utp.proyecto.service.ventas.DetalleVentaService;

import java.util.List;

/**
 * Controlador REST para la gestion de detalles de venta.
 */
@RestController
@RequestMapping("/ventas/detalles")
@Tag(name = "Detalles de Venta", description = "Gestion de detalles de venta")
@RequiredArgsConstructor
@Slf4j
public class DetalleVentaController {

    private final DetalleVentaService detalleService;

    /**
     * Crea un nuevo detalle de venta.
     * @param detalle Datos del detalle.
     * @return Detalle creado.
     */
    @Operation(summary = "Crear un nuevo detalle de venta")
    @PostMapping
    public ResponseEntity<ApiResponse<DetalleVenta>> crearDetalle(@Valid @RequestBody DetalleVenta detalle) {
        log.info("POST /ventas/detalles - Creando nuevo detalle de venta");
        DetalleVenta creado = detalleService.guardarDetalle(detalle);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Detalle creado exitosamente"));
    }

    /**
     * Obtiene un detalle por su ID.
     * @param id ID del detalle.
     * @return Detalle encontrado o 404.
     */
    @Operation(summary = "Obtener un detalle por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleVenta>> obtenerDetalle(@PathVariable Integer id) {
        log.info("GET /ventas/detalles/{} - Obteniendo detalle", id);
        return detalleService.obtenerDetallePorId(id)
                .map(detalle -> ResponseEntity.ok(ApiResponse.success(detalle)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los detalles.
     * @return Lista de detalles.
     */
    @Operation(summary = "Obtener todos los detalles")
    @GetMapping
    public ResponseEntity<ApiResponse<List<DetalleVenta>>> obtenerTodosDetalles() {
        log.info("GET /ventas/detalles - Obteniendo todos los detalles");
        List<DetalleVenta> detalles = detalleService.obtenerTodosLosDetalles();
        return ResponseEntity.ok(ApiResponse.success(detalles));
    }

    /**
     * Actualiza un detalle existente.
     * @param id ID del detalle.
     * @param detalle Datos actualizados.
     * @return Detalle actualizado.
     */
    @Operation(summary = "Actualizar un detalle existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DetalleVenta>> actualizarDetalle(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVenta detalle) {
        log.info("PUT /ventas/detalles/{} - Actualizando detalle", id);
        DetalleVenta actualizado = detalleService.actualizarDetalle(id, detalle);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Detalle actualizado exitosamente"));
    }

    /**
     * Elimina un detalle por su ID.
     * @param id ID del detalle a eliminar.
     */
    @Operation(summary = "Eliminar un detalle por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarDetalle(@PathVariable Integer id) {
        log.info("DELETE /ventas/detalles/{} - Eliminando detalle", id);
        detalleService.eliminarDetalle(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Detalle eliminado exitosamente"));
    }

    /**
     * Busca detalles de venta de un producto especifico.
     * @param idProducto ID del producto.
     * @return Lista de detalles del producto.
     */
    @Operation(summary = "Obtener detalles de venta por producto")
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ApiResponse<List<DetalleVenta>>> obtenerDetallesPorProducto(@PathVariable Integer idProducto) {
        log.info("GET /ventas/detalles/producto/{} - Obteniendo detalles por producto", idProducto);
        List<DetalleVenta> detalles = detalleService.obtenerDetallesPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(detalles));
    }

    /**
     * Busca detalles de venta cuyo subtotal este en un rango.
     * @param min Valor minimo del subtotal.
     * @param max Valor maximo del subtotal.
     * @return Lista de detalles en el rango.
     */
    @Operation(summary = "Buscar detalles por rango de subtotal")
    @GetMapping("/rango-subtotal")
    public ResponseEntity<ApiResponse<List<DetalleVenta>>> obtenerDetallesPorRangoSubtotal(
            @RequestParam Double min,
            @RequestParam Double max) {
        log.info("GET /ventas/detalles/rango-subtotal - Buscando detalles con subtotal entre {} y {}", min, max);
        List<DetalleVenta> detalles = detalleService.obtenerDetallesPorRangoSubtotal(min, max);
        return ResponseEntity.ok(ApiResponse.success(detalles));
    }

    /**
     * Suma el subtotal de todos los detalles de un producto.
     * @param idProducto ID del producto.
     * @return Suma de los subtotales.
     */
    @Operation(summary = "Sumar subtotal de detalles por producto")
    @GetMapping("/suma/producto/{idProducto}")
    public ResponseEntity<ApiResponse<Double>> sumarSubtotalPorProducto(@PathVariable Integer idProducto) {
        log.info("GET /ventas/detalles/suma/producto/{} - Sumando subtotal por producto", idProducto);
        Double suma = detalleService.sumarSubtotalPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(suma));
    }

    /**
     * Calcula el subtotal de un detalle.
     * @param detalle Detalle a calcular.
     * @return Detalle con el subtotal calculado.
     */
    @Operation(summary = "Calcular subtotal de un detalle")
    @PostMapping("/calcular")
    public ResponseEntity<ApiResponse<DetalleVenta>> calcularSubtotal(@Valid @RequestBody DetalleVenta detalle) {
        log.info("POST /ventas/detalles/calcular - Calculando subtotal del detalle");
        DetalleVenta calculado = detalleService.calcularSubtotal(detalle);
        return ResponseEntity.ok(ApiResponse.success(calculado, "Subtotal calculado exitosamente"));
    }
}