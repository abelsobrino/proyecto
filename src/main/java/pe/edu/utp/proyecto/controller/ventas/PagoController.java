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
import pe.edu.utp.proyecto.service.patron.creacional_factory.PagoEfectivoFactory;
import pe.edu.utp.proyecto.service.patron.creacional_factory.PagoTarjetaFactory;
import pe.edu.utp.proyecto.service.patron.creacional_factory.ProcesadorPagoFactory;
import pe.edu.utp.proyecto.service.ventas.PagoService;

import java.util.List;

/**
 * Controlador REST para la gestion de pagos.
 * Utiliza el patron Factory Method para crear diferentes tipos de pago.
 *
 * <p>Este controller equivale al main() del ejemplo del profesor:
 * <pre>
 * Logistica logisticaTerrestre = new LogisticaTerrestre();
 * logisticaTerrestre.planificarEntrega();
 * </pre>
 * </p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/ventas/pagos")
@Tag(name = "Pagos", description = "Gestion de pagos")
@RequiredArgsConstructor
@Slf4j
public class PagoController {

    private final PagoService pagoService;

    /**
     * Registra un nuevo pago en el sistema.
     * @param pago Datos del pago a registrar.
     * @return Pago registrado con su ID generado.
     */
    @Operation(summary = "Registrar un nuevo pago")
    @PostMapping
    public ResponseEntity<ApiResponse<Pago>> crearPago(@Valid @RequestBody Pago pago) {
        log.info("POST /ventas/pagos - Registrando nuevo pago");
        Pago creado = pagoService.guardarPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Pago registrado exitosamente"));
    }

    /**
     * Procesa un pago segun el tipo (EFECTIVO/TARJETA).
     *
     * <p>Este metodo es el equivalente al main() del ejemplo del profesor.
     * Demuestra el uso del patron Factory Method:
     * 1. Se crea una fabrica concreta segun el tipo
     * 2. Se llama al metodo procesar() que internamente usa el Factory Method
     *
     * <p>Equivale a:
     * <pre>
     * Logistica logisticaTerrestre = new LogisticaTerrestre();
     * logisticaTerrestre.planificarEntrega();
     * </pre>
     * </p>
     *
     * @param tipo Tipo de pago: EFECTIVO o TARJETA.
     * @return Mensaje de confirmacion del pago procesado.
     */
    @Operation(summary = "Procesar un pago segun el tipo (EFECTIVO/TARJETA) - Factory Method")
    @PostMapping("/procesar")
    public ResponseEntity<ApiResponse<String>> procesarPago(@RequestParam String tipo) {
        log.info("POST /ventas/pagos/procesar - Procesando pago tipo: {}", tipo);

        // === USO DEL PATRON FACTORY METHOD ===
        // 1. Crear la fabrica concreta segun el tipo de pago
        //    Equivale a: Logistica logistica = new LogisticaTerrestre();
        ProcesadorPagoFactory factory;
        if ("TARJETA".equalsIgnoreCase(tipo)) {
            factory = new PagoTarjetaFactory();
        } else {
            factory = new PagoEfectivoFactory();
        }

        // 2. Procesar el pago usando la fabrica (Template Method)
        //    Equivale a: logistica.planificarEntrega();
        factory.procesar();
        // === FIN FACTORY METHOD ===

        return ResponseEntity.ok(ApiResponse.success("Pago procesado correctamente", "Tipo: " + tipo));
    }

    /**
     * Obtiene un pago por su ID.
     * @param id ID del pago.
     * @return Pago encontrado o 404 Not Found.
     */
    @Operation(summary = "Obtener un pago por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pago>> obtenerPago(@PathVariable Integer id) {
        log.info("GET /ventas/pagos/{} - Obteniendo pago", id);
        return pagoService.obtenerPagoPorId(id)
                .map(pago -> ResponseEntity.ok(ApiResponse.success(pago)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los pagos registrados.
     * @return Lista de pagos.
     */
    @Operation(summary = "Obtener todos los pagos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Pago>>> obtenerTodosPagos() {
        log.info("GET /ventas/pagos - Obteniendo todos los pagos");
        List<Pago> pagos = pagoService.obtenerTodosLosPagos();
        return ResponseEntity.ok(ApiResponse.success(pagos));
    }

    /**
     * Actualiza un pago existente.
     * @param id ID del pago a actualizar.
     * @param pago Datos actualizados.
     * @return Pago actualizado.
     */
    @Operation(summary = "Actualizar un pago existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Pago>> actualizarPago(
            @PathVariable Integer id,
            @Valid @RequestBody Pago pago) {
        log.info("PUT /ventas/pagos/{} - Actualizando pago", id);
        Pago actualizado = pagoService.actualizarPago(id, pago);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Pago actualizado exitosamente"));
    }

    /**
     * Elimina un pago por su ID.
     * @param id ID del pago a eliminar.
     */
    @Operation(summary = "Eliminar un pago por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPago(@PathVariable Integer id) {
        log.info("DELETE /ventas/pagos/{} - Eliminando pago", id);
        pagoService.eliminarPago(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Pago eliminado exitosamente"));
    }

    /**
     * Busca pagos con monto mayor o igual al especificado.
     * @param montoMinimo Monto minimo.
     * @return Lista de pagos que coinciden.
     */
    @Operation(summary = "Buscar pagos por monto minimo")
    @GetMapping("/monto-minimo/{montoMinimo}")
    public ResponseEntity<ApiResponse<List<Pago>>> obtenerPagosPorMonto(@PathVariable Double montoMinimo) {
        log.info("GET /ventas/pagos/monto-minimo/{} - Buscando pagos con monto minimo", montoMinimo);
        List<Pago> pagos = pagoService.obtenerPagosPorMonto(montoMinimo);
        return ResponseEntity.ok(ApiResponse.success(pagos));
    }

    /**
     * Calcula el monto total de todos los pagos.
     * @return Suma de todos los montos.
     */
    @Operation(summary = "Calcular monto total de todos los pagos")
    @GetMapping("/total")
    public ResponseEntity<ApiResponse<Double>> calcularTotalPagos() {
        log.info("GET /ventas/pagos/total - Calculando total de pagos");
        Double total = pagoService.calcularMontoTotalPagos();
        return ResponseEntity.ok(ApiResponse.success(total, "Total calculado exitosamente"));
    }
}