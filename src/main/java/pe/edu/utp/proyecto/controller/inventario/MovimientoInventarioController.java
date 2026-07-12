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
import java.util.List;

@RestController
@RequestMapping("/inventario/movimientos")
@Tag(name = "Movimientos Inventario", description = "Gestion de movimientos de inventario")
@RequiredArgsConstructor
@Slf4j
public class MovimientoInventarioController {

    private final MovimientoInventarioService movimientoService;

    @Operation(summary = "Registrar un nuevo movimiento de inventario")
    @PostMapping
    public ResponseEntity<ApiResponse<MovimientoInventario>> crearMovimiento(@Valid @RequestBody MovimientoInventario movimiento) {
        log.info("POST /inventario/movimientos - Registrando nuevo movimiento");
        MovimientoInventario creado = movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Movimiento registrado exitosamente"));
    }

    @Operation(summary = "Obtener un movimiento por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoInventario>> obtenerMovimiento(@PathVariable Integer id) {
        log.info("GET /inventario/movimientos/{} - Obteniendo movimiento", id);
        return movimientoService.obtenerMovimientoPorId(id)
                .map(movimiento -> ResponseEntity.ok(ApiResponse.success(movimiento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los movimientos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> obtenerTodosMovimientos() {
        log.info("GET /inventario/movimientos - Obteniendo todos los movimientos");
        List<MovimientoInventario> movimientos = movimientoService.obtenerTodosLosMovimientos();
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    @Operation(summary = "Actualizar un movimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientoInventario>> actualizarMovimiento(
            @PathVariable Integer id,
            @Valid @RequestBody MovimientoInventario movimiento) {
        log.info("PUT /inventario/movimientos/{} - Actualizando movimiento", id);
        MovimientoInventario actualizado = movimientoService.actualizarMovimiento(id, movimiento);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Movimiento actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un movimiento por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarMovimiento(@PathVariable Integer id) {
        log.info("DELETE /inventario/movimientos/{} - Eliminando movimiento", id);
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Movimiento eliminado exitosamente"));
    }

    @Operation(summary = "Obtener movimientos por tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> obtenerPorTipo(@PathVariable String tipo) {
        log.info("GET /inventario/movimientos/tipo/{} - Obteniendo movimientos por tipo", tipo);
        List<MovimientoInventario> movimientos = movimientoService.obtenerPorTipo(tipo);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }

    @Operation(summary = "Obtener movimientos de un producto")
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ApiResponse<List<MovimientoInventario>>> obtenerPorProducto(@PathVariable Integer idProducto) {
        log.info("GET /inventario/movimientos/producto/{} - Obteniendo movimientos del producto", idProducto);
        List<MovimientoInventario> movimientos = movimientoService.obtenerPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(movimientos));
    }
}