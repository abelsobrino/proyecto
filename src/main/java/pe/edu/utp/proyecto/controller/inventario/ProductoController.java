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
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import pe.edu.utp.proyecto.service.inventario.ProductoService;

import java.util.List;

/**
 * Controlador REST para la gestion de productos.
 */
@RestController
@RequestMapping("/inventario/productos")
@Tag(name = "Productos", description = "Gestion de productos")
@RequiredArgsConstructor
@Slf4j
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Crea un nuevo producto.
     * @param producto Datos del producto.
     * @return Producto creado.
     */
    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> crearProducto(@Valid @RequestBody Producto producto) {
        log.info("POST /inventario/productos - Creando nuevo producto");
        Producto creado = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Producto creado exitosamente"));
    }

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto.
     * @return Producto encontrado o 404.
     */
    @Operation(summary = "Obtener un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> obtenerProducto(@PathVariable Integer id) {
        log.info("GET /inventario/productos/{} - Obteniendo producto", id);
        return productoService.obtenerProductoPorId(id)
                .map(producto -> ResponseEntity.ok(ApiResponse.success(producto)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los productos.
     * @return Lista de productos.
     */
    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerTodosProductos() {
        log.info("GET /inventario/productos - Obteniendo todos los productos");
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(ApiResponse.success(productos));
    }

    /**
     * Actualiza un producto existente.
     * @param id ID del producto.
     * @param producto Datos actualizados.
     * @return Producto actualizado.
     */
    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> actualizarProducto(
            @PathVariable Integer id,
            @Valid @RequestBody Producto producto) {
        log.info("PUT /inventario/productos/{} - Actualizando producto", id);
        Producto actualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Producto actualizado exitosamente"));
    }

    /**
     * Elimina un producto por su ID.
     * @param id ID del producto a eliminar.
     */
    @Operation(summary = "Eliminar un producto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarProducto(@PathVariable Integer id) {
        log.info("DELETE /inventario/productos/{} - Eliminando producto", id);
        productoService.eliminarProducto(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Producto eliminado exitosamente"));
    }

    /**
     * Busca un producto por su nombre exacto.
     * @param nombre Nombre del producto.
     * @return Producto encontrado o 404.
     */
    @Operation(summary = "Buscar producto por nombre exacto")
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<ApiResponse<Producto>> buscarPorNombre(@PathVariable String nombre) {
        log.info("GET /inventario/productos/buscar/nombre/{} - Buscando por nombre exacto", nombre);
        Producto producto = productoService.buscarPorNombre(nombre);
        if (producto != null) {
            return ResponseEntity.ok(ApiResponse.success(producto));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Obtiene productos con stock bajo.
     * @param stockMinimo Valor maximo de stock.
     * @return Lista de productos.
     */
    @Operation(summary = "Obtener productos con stock bajo")
    @GetMapping("/stock-bajo")
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerStockBajo(@RequestParam Integer stockMinimo) {
        log.info("GET /inventario/productos/stock-bajo - Productos con stock menor a: {}", stockMinimo);
        List<Producto> productos = productoService.obtenerProductosConStockBajo(stockMinimo);
        return ResponseEntity.ok(ApiResponse.success(productos));
    }

    /**
     * Busca productos cuyo nombre contenga un texto.
     * @param texto Texto a buscar en el nombre.
     * @return Lista de productos que coinciden.
     */
    @Operation(summary = "Buscar productos por nombre que contenga")
    @GetMapping("/buscar/contiene/{texto}")
    public ResponseEntity<ApiResponse<List<Producto>>> buscarPorNombreContaining(@PathVariable String texto) {
        log.info("GET /inventario/productos/buscar/contiene/{} - Buscando por nombre que contenga", texto);
        List<Producto> productos = productoService.buscarPorNombreContaining(texto);
        return ResponseEntity.ok(ApiResponse.success(productos));
    }
}