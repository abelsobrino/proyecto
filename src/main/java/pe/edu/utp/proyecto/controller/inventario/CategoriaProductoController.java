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
import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;
import pe.edu.utp.proyecto.service.inventario.CategoriaProductoService;

import java.util.List;

/**
 * Controlador REST para la gestion de categorias de productos.
 */
@RestController
@RequestMapping("/inventario/categorias")
@Tag(name = "Categorias", description = "Gestion de categorias de productos")
@RequiredArgsConstructor
@Slf4j
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaService;

    /**
     * Crea una nueva categoria.
     * @param categoria Datos de la categoria.
     * @return Categoria creada.
     */
    @Operation(summary = "Crear una nueva categoria")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaProducto>> crearCategoria(@Valid @RequestBody CategoriaProducto categoria) {
        log.info("POST /inventario/categorias - Creando nueva categoria");
        CategoriaProducto creada = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Categoria creada exitosamente"));
    }

    /**
     * Obtiene una categoria por su ID.
     * @param id ID de la categoria.
     * @return Categoria encontrada o 404.
     */
    @Operation(summary = "Obtener una categoria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaProducto>> obtenerCategoria(@PathVariable Integer id) {
        log.info("GET /inventario/categorias/{} - Obteniendo categoria", id);
        return categoriaService.obtenerCategoriaPorId(id)
                .map(categoria -> ResponseEntity.ok(ApiResponse.success(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todas las categorias.
     * @return Lista de categorias.
     */
    @Operation(summary = "Obtener todas las categorias")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaProducto>>> obtenerTodasCategorias() {
        log.info("GET /inventario/categorias - Obteniendo todas las categorias");
        List<CategoriaProducto> categorias = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(ApiResponse.success(categorias));
    }

    /**
     * Actualiza una categoria existente.
     * @param id ID de la categoria.
     * @param categoria Datos actualizados.
     * @return Categoria actualizada.
     */
    @Operation(summary = "Actualizar una categoria existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaProducto>> actualizarCategoria(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaProducto categoria) {
        log.info("PUT /inventario/categorias/{} - Actualizando categoria", id);
        CategoriaProducto actualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Categoria actualizada exitosamente"));
    }

    /**
     * Elimina una categoria por su ID.
     * @param id ID de la categoria a eliminar.
     */
    @Operation(summary = "Eliminar una categoria por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCategoria(@PathVariable Integer id) {
        log.info("DELETE /inventario/categorias/{} - Eliminando categoria", id);
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Categoria eliminada exitosamente"));
    }

    /**
     * Busca categorias por nombre.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de categorias que coinciden.
     */
    @Operation(summary = "Buscar categorias por nombre")
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<CategoriaProducto>>> buscarPorNombre(@PathVariable String nombre) {
        log.info("GET /inventario/categorias/buscar/nombre/{} - Buscando por nombre", nombre);
        List<CategoriaProducto> categorias = categoriaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(ApiResponse.success(categorias));
    }

    /**
     * Busca categorias por descripcion.
     * @param descripcion Texto a buscar en la descripcion.
     * @return Lista de categorias que coinciden.
     */
    @Operation(summary = "Buscar categorias por descripcion")
    @GetMapping("/buscar/descripcion/{descripcion}")
    public ResponseEntity<ApiResponse<List<CategoriaProducto>>> buscarPorDescripcion(@PathVariable String descripcion) {
        log.info("GET /inventario/categorias/buscar/descripcion/{} - Buscando por descripcion", descripcion);
        List<CategoriaProducto> categorias = categoriaService.buscarPorDescripcion(descripcion);
        return ResponseEntity.ok(ApiResponse.success(categorias));
    }
}