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
import pe.edu.utp.proyecto.modelo.inventario.Inventario;
import pe.edu.utp.proyecto.service.inventario.InventarioService;

import java.util.List;

/**
 * Controlador REST para la gestion de inventarios.
 */
@RestController
@RequestMapping("/inventario/inventarios")
@Tag(name = "Inventarios", description = "Gestion de inventarios")
@RequiredArgsConstructor
@Slf4j
public class InventarioController {

    private final InventarioService inventarioService;

    /**
     * Crea un nuevo inventario.
     * @param inventario Datos del inventario.
     * @return Inventario creado.
     */
    @Operation(summary = "Crear un nuevo inventario")
    @PostMapping
    public ResponseEntity<ApiResponse<Inventario>> crearInventario(@Valid @RequestBody Inventario inventario) {
        log.info("POST /inventario/inventarios - Creando nuevo inventario");
        Inventario creado = inventarioService.guardarInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Inventario creado exitosamente"));
    }

    /**
     * Obtiene un inventario por su ID.
     * @param id ID del inventario.
     * @return Inventario encontrado o 404.
     */
    @Operation(summary = "Obtener un inventario por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventario>> obtenerInventario(@PathVariable Integer id) {
        log.info("GET /inventario/inventarios/{} - Obteniendo inventario", id);
        return inventarioService.obtenerInventarioPorId(id)
                .map(inventario -> ResponseEntity.ok(ApiResponse.success(inventario)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los inventarios.
     * @return Lista de inventarios.
     */
    @Operation(summary = "Obtener todos los inventarios")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Inventario>>> obtenerTodosInventarios() {
        log.info("GET /inventario/inventarios - Obteniendo todos los inventarios");
        List<Inventario> inventarios = inventarioService.obtenerTodosLosInventarios();
        return ResponseEntity.ok(ApiResponse.success(inventarios));
    }

    /**
     * Actualiza un inventario existente.
     * @param id ID del inventario.
     * @param inventario Datos actualizados.
     * @return Inventario actualizado.
     */
    @Operation(summary = "Actualizar un inventario existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventario>> actualizarInventario(
            @PathVariable Integer id,
            @Valid @RequestBody Inventario inventario) {
        log.info("PUT /inventario/inventarios/{} - Actualizando inventario", id);
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Inventario actualizado exitosamente"));
    }

    /**
     * Elimina un inventario por su ID.
     * @param id ID del inventario a eliminar.
     */
    @Operation(summary = "Eliminar un inventario por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarInventario(@PathVariable Integer id) {
        log.info("DELETE /inventario/inventarios/{} - Eliminando inventario", id);
        inventarioService.eliminarInventario(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Inventario eliminado exitosamente"));
    }

    /**
     * Busca inventarios por nombre.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de inventarios que coinciden.
     */
    @Operation(summary = "Buscar inventarios por nombre")
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<Inventario>>> buscarPorNombre(@PathVariable String nombre) {
        log.info("GET /inventario/inventarios/buscar/nombre/{} - Buscando por nombre", nombre);
        List<Inventario> inventarios = inventarioService.buscarPorNombre(nombre);
        return ResponseEntity.ok(ApiResponse.success(inventarios));
    }

    /**
     * Busca inventarios por descripcion.
     * @param descripcion Texto a buscar en la descripcion.
     * @return Lista de inventarios que coinciden.
     */
    @Operation(summary = "Buscar inventarios por descripcion")
    @GetMapping("/buscar/descripcion/{descripcion}")
    public ResponseEntity<ApiResponse<List<Inventario>>> buscarPorDescripcion(@PathVariable String descripcion) {
        log.info("GET /inventario/inventarios/buscar/descripcion/{} - Buscando por descripcion", descripcion);
        List<Inventario> inventarios = inventarioService.buscarPorDescripcion(descripcion);
        return ResponseEntity.ok(ApiResponse.success(inventarios));
    }
}