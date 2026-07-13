package pe.edu.utp.proyecto.controller.comprobantes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.comprobantes.EnvioSUNAT;
import pe.edu.utp.proyecto.service.comprobantes.EnvioSUNATService;

import java.util.List;

/**
 * Controlador REST para la gestion de envios a SUNAT.
 * Expone endpoints para operaciones CRUD de envios.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/comprobantes/envios-sunat")
@Tag(name = "Envios SUNAT", description = "Gestion de envios a SUNAT")
@RequiredArgsConstructor
@Slf4j
public class EnvioSUNATController {

    private final EnvioSUNATService envioSUNATService;

    @Operation(summary = "Registrar un nuevo envio a SUNAT")
    @PostMapping
    public ResponseEntity<ApiResponse<EnvioSUNAT>> crearEnvio(@Valid @RequestBody EnvioSUNAT envio) {
        log.info("POST /comprobantes/envios-sunat - Creando nuevo envio a SUNAT");
        EnvioSUNAT creado = envioSUNATService.guardarEnvioSUNAT(envio);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Envio a SUNAT creado exitosamente"));
    }

    @Operation(summary = "Obtener un envio por su codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ApiResponse<EnvioSUNAT>> obtenerEnvio(@PathVariable String codigo) {
        log.info("GET /comprobantes/envios-sunat/{} - Obteniendo envio", codigo);
        return envioSUNATService.obtenerEnvioSUNATPorId(codigo)
                .map(envio -> ResponseEntity.ok(ApiResponse.success(envio)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los envios a SUNAT")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EnvioSUNAT>>> obtenerTodosEnvios() {
        log.info("GET /comprobantes/envios-sunat - Obteniendo todos los envios a SUNAT");
        List<EnvioSUNAT> envios = envioSUNATService.obtenerTodosLosEnviosSUNAT();
        return ResponseEntity.ok(ApiResponse.success(envios));
    }

    @Operation(summary = "Actualizar un envio existente")
    @PutMapping("/{codigo}")
    public ResponseEntity<ApiResponse<EnvioSUNAT>> actualizarEnvio(
            @PathVariable String codigo,
            @Valid @RequestBody EnvioSUNAT envio) {
        log.info("PUT /comprobantes/envios-sunat/{} - Actualizando envio", codigo);
        EnvioSUNAT actualizado = envioSUNATService.actualizarEnvioSUNAT(codigo, envio);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Envio actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un envio por su codigo")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<ApiResponse<Void>> eliminarEnvio(@PathVariable String codigo) {
        log.info("DELETE /comprobantes/envios-sunat/{} - Eliminando envio", codigo);
        envioSUNATService.eliminarEnvioSUNAT(codigo);
        return ResponseEntity.ok(ApiResponse.success(null, "Envio eliminado exitosamente"));
    }
}