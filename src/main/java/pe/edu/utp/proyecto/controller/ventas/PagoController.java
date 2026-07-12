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
import pe.edu.utp.proyecto.modelo.ventas.Pago;
import pe.edu.utp.proyecto.service.patron.creacional_factory.PagoFactory;
import pe.edu.utp.proyecto.service.patron.creacional_factory.ProcesadorPago;
import pe.edu.utp.proyecto.service.ventas.PagoService;

import java.util.List;

@RestController
@RequestMapping("/ventas/pagos")
@Tag(name = "Pagos", description = "Gestion de pagos")
@RequiredArgsConstructor
@Slf4j
public class PagoController {

    private final PagoService pagoService;

    @Operation(summary = "Registrar un nuevo pago")
    @PostMapping
    public ResponseEntity<ApiResponse<Pago>> crearPago(@Valid @RequestBody Pago pago) {
        log.info("POST /ventas/pagos - Registrando nuevo pago");
        Pago creado = pagoService.guardarPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Pago registrado exitosamente"));
    }

    // USO DEL PATRON FACTORY
    @Operation(summary = "Procesar un pago segun el tipo (EFECTIVO/TARJETA)")
    @PostMapping("/procesar")
    public ResponseEntity<ApiResponse<String>> procesarPago(@RequestParam String tipo) {
        log.info("POST /ventas/pagos/procesar - Procesando pago tipo: {}", tipo);

        // USO DEL PATRON FACTORY
        ProcesadorPago pago = PagoFactory.crearPago(tipo);
        pago.procesarPago();

        return ResponseEntity.ok(ApiResponse.success("Pago procesado correctamente", "Tipo: " + tipo));
    }

    @Operation(summary = "Obtener un pago por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pago>> obtenerPago(@PathVariable Integer id) {
        log.info("GET /ventas/pagos/{} - Obteniendo pago", id);
        return pagoService.obtenerPagoPorId(id)
                .map(pago -> ResponseEntity.ok(ApiResponse.success(pago)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los pagos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Pago>>> obtenerTodosPagos() {
        log.info("GET /ventas/pagos - Obteniendo todos los pagos");
        List<Pago> pagos = pagoService.obtenerTodosLosPagos();
        return ResponseEntity.ok(ApiResponse.success(pagos));
    }

    @Operation(summary = "Actualizar un pago existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Pago>> actualizarPago(
            @PathVariable Integer id,
            @Valid @RequestBody Pago pago) {
        log.info("PUT /ventas/pagos/{} - Actualizando pago", id);
        Pago actualizado = pagoService.actualizarPago(id, pago);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Pago actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un pago por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPago(@PathVariable Integer id) {
        log.info("DELETE /ventas/pagos/{} - Eliminando pago", id);
        pagoService.eliminarPago(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Pago eliminado exitosamente"));
    }

    @Operation(summary = "Calcular monto total de todos los pagos")
    @GetMapping("/total")
    public ResponseEntity<ApiResponse<Double>> calcularTotalPagos() {
        log.info("GET /ventas/pagos/total - Calculando total de pagos");
        Double total = pagoService.calcularMontoTotalPagos();
        return ResponseEntity.ok(ApiResponse.success(total, "Total calculado exitosamente"));
    }
}