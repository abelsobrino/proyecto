package pe.edu.utp.proyecto.controller.inventario_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;
import pe.edu.utp.proyecto.service.inventario.LoteProductoService;
import java.util.List;

@RestController
@RequestMapping("/inventario/lotes")
@Tag(name = "Lotes", description = "Gestion de lotes de productos")
@RequiredArgsConstructor
@Slf4j
public class LoteProductoController {

    private final LoteProductoService loteService;

    @Operation(summary = "Crear un nuevo lote")
    @PostMapping
    public ResponseEntity<ApiResponse<LoteProducto>> crearLote(@Valid @RequestBody LoteProducto lote) {
        log.info("POST /inventario/lotes - Creando nuevo lote");
        LoteProducto creado = loteService.guardarLote(lote);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Lote creado exitosamente"));
    }

    @Operation(summary = "Obtener un lote por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoteProducto>> obtenerLote(@PathVariable Integer id) {
        log.info("GET /inventario/lotes/{} - Obteniendo lote", id);
        return loteService.obtenerLotePorId(id)
                .map(lote -> ResponseEntity.ok(ApiResponse.success(lote)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los lotes")
    @GetMapping
    public ResponseEntity<ApiResponse<List<LoteProducto>>> obtenerTodosLotes() {
        log.info("GET /inventario/lotes - Obteniendo todos los lotes");
        List<LoteProducto> lotes = loteService.obtenerTodosLosLotes();
        return ResponseEntity.ok(ApiResponse.success(lotes));
    }

    @Operation(summary = "Actualizar un lote existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LoteProducto>> actualizarLote(
            @PathVariable Integer id,
            @Valid @RequestBody LoteProducto lote) {
        log.info("PUT /inventario/lotes/{} - Actualizando lote", id);
        LoteProducto actualizado = loteService.actualizarLote(id, lote);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Lote actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un lote por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarLote(@PathVariable Integer id) {
        log.info("DELETE /inventario/lotes/{} - Eliminando lote", id);
        loteService.eliminarLote(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Lote eliminado exitosamente"));
    }
}