package pe.edu.utp.proyecto.controller.empresa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.empresa.Proveedor;
import pe.edu.utp.proyecto.service.empresa.ProveedorService;
import java.util.List;

@RestController
@RequestMapping("/empresa/proveedores")
@Tag(name = "Proveedores", description = "Gestion de proveedores")
@RequiredArgsConstructor
@Slf4j
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Operation(summary = "Crear un nuevo proveedor")
    @PostMapping
    public ResponseEntity<ApiResponse<Proveedor>> crearProveedor(@Valid @RequestBody Proveedor proveedor) {
        log.info("POST /empresa/proveedores - Creando nuevo proveedor");
        Proveedor creado = proveedorService.guardarProveedor(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Proveedor creado exitosamente"));
    }

    @Operation(summary = "Obtener un proveedor por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Proveedor>> obtenerProveedor(@PathVariable Integer id) {
        log.info("GET /empresa/proveedores/{} - Obteniendo proveedor", id);
        return proveedorService.obtenerProveedorPorId(id)
                .map(proveedor -> ResponseEntity.ok(ApiResponse.success(proveedor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los proveedores")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Proveedor>>> obtenerTodosProveedores() {
        log.info("GET /empresa/proveedores - Obteniendo todos los proveedores");
        List<Proveedor> proveedores = proveedorService.obtenerTodosLosProveedores();
        return ResponseEntity.ok(ApiResponse.success(proveedores));
    }

    @Operation(summary = "Actualizar un proveedor existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Proveedor>> actualizarProveedor(
            @PathVariable Integer id,
            @Valid @RequestBody Proveedor proveedor) {
        log.info("PUT /empresa/proveedores/{} - Actualizando proveedor", id);
        Proveedor actualizado = proveedorService.actualizarProveedor(id, proveedor);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Proveedor actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un proveedor por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarProveedor(@PathVariable Integer id) {
        log.info("DELETE /empresa/proveedores/{} - Eliminando proveedor", id);
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Proveedor eliminado exitosamente"));
    }
}