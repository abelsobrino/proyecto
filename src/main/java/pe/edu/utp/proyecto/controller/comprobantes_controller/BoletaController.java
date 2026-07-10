package pe.edu.utp.proyecto.controller.comprobantes_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.dto.BoletaDTO;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;
import pe.edu.utp.proyecto.service.comprobantes_service.BoletaService;
import pe.edu.utp.proyecto.service.patron.estructural_decorator.ComprobanteConQR;

import java.util.List;

@RestController
@RequestMapping("/comprobantes/boletas")
@Tag(name = "Boletas", description = "Gestion de boletas de venta")
@RequiredArgsConstructor
@Slf4j
public class BoletaController {

    private final BoletaService boletaService;

    @Operation(summary = "Crear una nueva boleta")
    @PostMapping
    public ResponseEntity<ApiResponse<BoletaDTO>> crearBoleta(@Valid @RequestBody Boleta boleta) {
        log.info("POST /comprobantes/boletas - Creando nueva boleta");
        Boleta creada = boletaService.guardarBoleta(boleta);
        BoletaDTO dto = convertirADTO(creada);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(dto, "Boleta creada exitosamente"));
    }

    @Operation(summary = "Obtener una boleta por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoletaDTO>> obtenerBoleta(@PathVariable Long id) {
        log.info("GET /comprobantes/boletas/{} - Obteniendo boleta", id);
        return boletaService.obtenerBoletaPorId(id)
                .map(boleta -> ResponseEntity.ok(ApiResponse.success(convertirADTO(boleta))))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las boletas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<BoletaDTO>>> obtenerTodasBoletas() {
        log.info("GET /comprobantes/boletas - Obteniendo todas las boletas");
        List<Boleta> boletas = boletaService.obtenerTodasLasBoletas();
        List<BoletaDTO> dtos = boletas.stream().map(this::convertirADTO).toList();
        return ResponseEntity.ok(ApiResponse.success(dtos));
    }

    @Operation(summary = "Actualizar una boleta existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BoletaDTO>> actualizarBoleta(
            @PathVariable Long id,
            @Valid @RequestBody Boleta boleta) {
        log.info("PUT /comprobantes/boletas/{} - Actualizando boleta", id);
        Boleta actualizada = boletaService.actualizarBoleta(id, boleta);
        return ResponseEntity.ok(ApiResponse.success(convertirADTO(actualizada), "Boleta actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una boleta por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarBoleta(@PathVariable Long id) {
        log.info("DELETE /comprobantes/boletas/{} - Eliminando boleta", id);
        boletaService.eliminarBoleta(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Boleta eliminada exitosamente"));
    }

    @Operation(summary = "Emitir boleta con codigo QR")
    @PostMapping("/emitir-con-qr")
    public ResponseEntity<ApiResponse<String>> emitirBoletaConQR(@Valid @RequestBody Boleta boleta) {
        log.info("POST /comprobantes/boletas/emitir-con-qr - Emitiendo boleta con QR");

        // USO DEL PATRON DECORATOR
        ComprobanteConQR boletaConQR = new ComprobanteConQR(boleta);
        boletaConQR.emitir();

        boletaService.guardarBoleta(boleta);
        return ResponseEntity.ok(ApiResponse.success("Boleta emitida con QR", "Serie: " + boleta.getSerie()));
    }

    // =============================================
    // METODO PARA CONVERTIR ENTIDAD A DTO
    // =============================================

    private BoletaDTO convertirADTO(Boleta boleta) {
        return new BoletaDTO(
                boleta.getIdBoleta(),
                boleta.getSerie(),
                boleta.getNumero(),
                boleta.getFechaEmision(),
                boleta.getTotal(),
                boleta.getDniCliente()
        );
    }
}