package pe.edu.utp.proyecto.controller.ventas_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;
import pe.edu.utp.proyecto.service.ventas_service.CarritoCompraService;
import java.util.List;

@RestController
@RequestMapping("/ventas/carritos")
@Tag(name = "Carritos de Compra", description = "Gestion de carritos de compra")
@RequiredArgsConstructor
@Slf4j
public class CarritoCompraController {

    private final CarritoCompraService carritoService;

    @Operation(summary = "Crear un nuevo carrito de compra")
    @PostMapping
    public ResponseEntity<ApiResponse<CarritoCompra>> crearCarrito(@Valid @RequestBody CarritoCompra carrito) {
        log.info("POST /ventas/carritos - Creando nuevo carrito de compra");
        CarritoCompra creado = carritoService.guardarCarrito(carrito);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Carrito creado exitosamente"));
    }

    @Operation(summary = "Obtener un carrito por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CarritoCompra>> obtenerCarrito(@PathVariable Integer id) {
        log.info("GET /ventas/carritos/{} - Obteniendo carrito", id);
        return carritoService.obtenerCarritoPorId(id)
                .map(carrito -> ResponseEntity.ok(ApiResponse.success(carrito)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los carritos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CarritoCompra>>> obtenerTodosCarritos() {
        log.info("GET /ventas/carritos - Obteniendo todos los carritos");
        List<CarritoCompra> carritos = carritoService.obtenerTodosLosCarritos();
        return ResponseEntity.ok(ApiResponse.success(carritos));
    }

    @Operation(summary = "Actualizar un carrito existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CarritoCompra>> actualizarCarrito(
            @PathVariable Integer id,
            @Valid @RequestBody CarritoCompra carrito) {
        log.info("PUT /ventas/carritos/{} - Actualizando carrito", id);
        CarritoCompra actualizado = carritoService.actualizarCarrito(id, carrito);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Carrito actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un carrito por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCarrito(@PathVariable Integer id) {
        log.info("DELETE /ventas/carritos/{} - Eliminando carrito", id);
        carritoService.eliminarCarrito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Carrito eliminado exitosamente"));
    }

    @Operation(summary = "Obtener carritos por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<ApiResponse<List<CarritoCompra>>> obtenerPorEstado(@PathVariable String estado) {
        log.info("GET /ventas/carritos/estado/{} - Obteniendo carritos por estado", estado);
        List<CarritoCompra> carritos = carritoService.obtenerPorEstado(estado);
        return ResponseEntity.ok(ApiResponse.success(carritos));
    }
}