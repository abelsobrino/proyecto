package pe.edu.utp.proyecto.controller.inventario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;
import pe.edu.utp.proyecto.service.inventario.MovimientoInventarioService;

import java.util.Date;
import java.util.List;

/**
 * Controlador REST para la gestion de movimientos de inventario.
 */
@RestController
@RequestMapping("/inventario/movimientos")
@Tag(name = "Movimientos Inventario", description = "Gestion de movimientos de inventario")
@RequiredArgsConstructor
@Slf4j
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    /**
     * Registra un nuevo movimiento de inventario.
     * @param movimiento Datos del movimiento.
     * @return Movimiento registrado.
     */
    @Operation(summary = "Registrar un nuevo movimiento de inventario")
    @PostMapping
    public ResponseEntity<ApiResponse<MovimientoInventario>> crearMovimiento(@Valid @RequestBody MovimientoInventario movimiento) {
        log.info("POST /inventario/movimientos - Registrando nuevo movimiento");
        MovimientoInventario creado = movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Movimiento registrado exitosamente"));
    }

    /**
     * Obtiene un movimiento por su ID.
     * @param id ID del movimiento.
     * @return Movimiento encontrado o 404.
     */
    @Operation(summary = "Obtener un movimiento por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoInventario>> obtenerMovimiento(@PathVariable Integer id) {
        log.info("GET /inventario/movimientos/{} - Obteniendo movimiento", id);
        return movimientoService.obtenerMovimientoPorId(id)
                .map(movimiento -> ResponseEntity.ok(ApiResponse.success(movimiento)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los movimientos.
     * @return Lista de movimientos.
     */
    @Operation(summary = "Obtener todos los movimientos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> obtenerTodosMovimientos() {
        log.info("GET /inventario/movimientos - Obteniendo todos los movimientos");
        List<MovimientoInventario> movimientos = movimientoService.obtenerTodosLosMovimientos();
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    /**
     * Actualiza un movimiento existente.
     * @param id ID del movimiento.
     * @param movimiento Datos actualizados.
     * @return Movimiento actualizado.
     */
    @Operation(summary = "Actualizar un movimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoInventario>> actualizarMovimiento(
            @PathVariable Integer id,
            @Valid @RequestBody MovimientoInventario movimiento) {
        log.info("PUT /inventario/movimientos/{} - Actualizando movimiento", id);
        MovimientoInventario actualizado = movimientoService.actualizarMovimiento(id, movimiento);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Movimiento actualizado exitosamente"));
    }

    /**
     * Elimina un movimiento por su ID.
     * @param id ID del movimiento a eliminar.
     */
    @Operation(summary = "Eliminar un movimiento por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarMovimiento(@PathVariable Integer id) {
        log.info("DELETE /inventario/movimientos/{} - Eliminando movimiento", id);
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Movimiento eliminado exitosamente"));
    }

    /**
     * Busca movimientos por tipo (ENTRADA/SALIDA).
     * @param tipo Tipo de movimiento.
     * @return Lista de movimientos del tipo especificado.
     */
    @Operation(summary = "Buscar movimientos por tipo")
    @GetMapping("/buscar/tipo/{tipo}")
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> buscarPorTipo(@PathVariable String tipo) {
        log.info("GET /inventario/movimientos/buscar/tipo/{} - Buscando por tipo", tipo);
        List<MovimientoInventario> movimientos = movimientoService.buscarPorTipo(tipo);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    /**
     * Busca movimientos de un producto especifico.
     * @param idProducto ID del producto.
     * @return Lista de movimientos del producto.
     */
    @Operation(summary = "Buscar movimientos por producto")
    @GetMapping("/buscar/producto/{idProducto}")
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> buscarPorProducto(@PathVariable Integer idProducto) {
        log.info("GET /inventario/movimientos/buscar/producto/{} - Buscando por producto", idProducto);
        List<MovimientoInventario> movimientos = movimientoService.buscarPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    /**
     * Busca movimientos entre dos fechas.
     * @param inicio Fecha de inicio.
     * @param fin Fecha de fin.
     * @return Lista de movimientos en el rango.
     */
    @Operation(summary = "Buscar movimientos entre fechas")
    @GetMapping("/buscar/entre-fechas")
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> buscarEntreFechas(
            @RequestParam Date inicio,
            @RequestParam Date fin) {
        log.info("GET /inventario/movimientos/buscar/entre-fechas - Buscando entre {} y {}", inicio, fin);
        List<MovimientoInventario> movimientos = movimientoService.buscarEntreFechas(inicio, fin);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }
}