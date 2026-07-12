package pe.edu.utp.proyecto.controller.ventas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;
import pe.edu.utp.proyecto.service.ventas.MetodoPagoService;
import java.util.List;

@RestController
@RequestMapping("/ventas/metodos-pago")
@Tag(name = "Metodos de Pago", description = "Gestion de metodos de pago")
@RequiredArgsConstructor
@Slf4j
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @Operation(summary = "Crear un nuevo metodo de pago")
    @PostMapping
    public ResponseEntity<ApiResponse<MetodoPago>> crearMetodoPago(@Valid @RequestBody MetodoPago metodoPago) {
        log.info("POST /ventas/metodos-pago - Creando nuevo metodo de pago");
        MetodoPago creado = metodoPagoService.guardarMetodoPago(metodoPago);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Metodo de pago creado exitosamente"));
    }

    @Operation(summary = "Obtener un metodo de pago por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MetodoPago>> obtenerMetodoPago(@PathVariable Integer id) {
        log.info("GET /ventas/metodos-pago/{} - Obteniendo metodo de pago", id);
        return metodoPagoService.obtenerMetodoPagoPorId(id)
                .map(metodo -> ResponseEntity.ok(ApiResponse.success(metodo)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los metodos de pago")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MetodoPago>>> obtenerTodosMetodosPago() {
        log.info("GET /ventas/metodos-pago - Obteniendo todos los metodos de pago");
        List<MetodoPago> metodos = metodoPagoService.obtenerTodosLosMetodosPago();
        return ResponseEntity.ok(ApiResponse.success(metodos));
    }

    @Operation(summary = "Actualizar un metodo de pago existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MetodoPago>> actualizarMetodoPago(
            @PathVariable Integer id,
            @Valid @RequestBody MetodoPago metodoPago) {
        log.info("PUT /ventas/metodos-pago/{} - Actualizando metodo de pago", id);
        MetodoPago actualizado = metodoPagoService.actualizarMetodoPago(id, metodoPago);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Metodo de pago actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un metodo de pago por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarMetodoPago(@PathVariable Integer id) {
        log.info("DELETE /ventas/metodos-pago/{} - Eliminando metodo de pago", id);
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Metodo de pago eliminado exitosamente"));
    }
}