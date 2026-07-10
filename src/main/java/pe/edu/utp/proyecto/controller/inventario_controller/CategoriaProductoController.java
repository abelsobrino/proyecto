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
import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;
import pe.edu.utp.proyecto.service.inventario_service.CategoriaProductoService;
import java.util.List;

@RestController
@RequestMapping("/inventario/categorias")
@Tag(name = "Categorias", description = "Gestion de categorias de productos")
@RequiredArgsConstructor
@Slf4j
public class CategoriaProductoController {

    private final CategoriaProductoService categoriaService;

    @Operation(summary = "Crear una nueva categoria")
    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaProducto>> crearCategoria(@Valid @RequestBody CategoriaProducto categoria) {
        log.info("POST /inventario/categorias - Creando nueva categoria");
        CategoriaProducto creada = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Categoria creada exitosamente"));
    }

    @Operation(summary = "Obtener una categoria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaProducto>> obtenerCategoria(@PathVariable Integer id) {
        log.info("GET /inventario/categorias/{} - Obteniendo categoria", id);
        return categoriaService.obtenerCategoriaPorId(id)
                .map(categoria -> ResponseEntity.ok(ApiResponse.success(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las categorias")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaProducto>>> obtenerTodasCategorias() {
        log.info("GET /inventario/categorias - Obteniendo todas las categorias");
        List<CategoriaProducto> categorias = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(ApiResponse.success(categorias));
    }

    @Operation(summary = "Actualizar una categoria existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaProducto>> actualizarCategoria(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaProducto categoria) {
        log.info("PUT /inventario/categorias/{} - Actualizando categoria", id);
        CategoriaProducto actualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Categoria actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una categoria por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCategoria(@PathVariable Integer id) {
        log.info("DELETE /inventario/categorias/{} - Eliminando categoria", id);
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Categoria eliminada exitosamente"));
    }
}