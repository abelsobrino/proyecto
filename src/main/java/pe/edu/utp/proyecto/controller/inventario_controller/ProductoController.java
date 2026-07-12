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
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import pe.edu.utp.proyecto.service.inventario.ProductoService;
import java.util.List;

@RestController
@RequestMapping("/inventario/productos")
@Tag(name = "Productos", description = "Gestion de productos")
@RequiredArgsConstructor
@Slf4j
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> crearProducto(@Valid @RequestBody Producto producto) {
        log.info("POST /inventario/productos - Creando nuevo producto");
        Producto creado = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Producto creado exitosamente"));
    }

    @Operation(summary = "Obtener un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> obtenerProducto(@PathVariable Integer id) {
        log.info("GET /inventario/productos/{} - Obteniendo producto", id);
        return productoService.obtenerProductoPorId(id)
                .map(producto -> ResponseEntity.ok(ApiResponse.success(producto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerTodosProductos() {
        log.info("GET /inventario/productos - Obteniendo todos los productos");
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(ApiResponse.success(productos));
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> actualizarProducto(
            @PathVariable Integer id,
            @Valid @RequestBody Producto producto) {
        log.info("PUT /inventario/productos/{} - Actualizando producto", id);
        Producto actualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Producto actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un producto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarProducto(@PathVariable Integer id) {
        log.info("DELETE /inventario/productos/{} - Eliminando producto", id);
        productoService.eliminarProducto(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Producto eliminado exitosamente"));
    }

    @Operation(summary = "Buscar producto por nombre")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<Producto>> buscarPorNombre(@RequestParam String nombre) {
        log.info("GET /inventario/productos/buscar - Buscando producto por nombre: {}", nombre);
        Producto producto = productoService.buscarPorNombre(nombre);
        if (producto != null) {
            return ResponseEntity.ok(ApiResponse.success(producto));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Productos con stock bajo")
    @GetMapping("/stock-bajo")
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerStockBajo(@RequestParam Integer stockMinimo) {
        log.info("GET /inventario/productos/stock-bajo - Productos con stock menor a: {}", stockMinimo);
        List<Producto> productos = productoService.obtenerProductosConStockBajo(stockMinimo);
        return ResponseEntity.ok(ApiResponse.success(productos));
    }
}