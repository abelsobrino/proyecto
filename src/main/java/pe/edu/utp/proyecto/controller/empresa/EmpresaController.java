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
import pe.edu.utp.proyecto.modelo.empresa.Empresa;
import pe.edu.utp.proyecto.service.empresa.EmpresaService;

import java.util.List;

/**
 * Controlador REST para la gestion de empresas.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/empresa/empresas")
@Tag(name = "Empresas", description = "Gestion de empresas")
@RequiredArgsConstructor
@Slf4j
public class EmpresaController {

    private final EmpresaService empresaService;

    @Operation(summary = "Crear una nueva empresa")
    @PostMapping
    public ResponseEntity<ApiResponse<Empresa>> crearEmpresa(@Valid @RequestBody Empresa empresa) {
        log.info("POST /empresa/empresas - Creando nueva empresa");
        Empresa creada = empresaService.guardarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creada, "Empresa creada exitosamente"));
    }

    @Operation(summary = "Obtener una empresa por su RUC")
    @GetMapping("/{ruc}")
    public ResponseEntity<ApiResponse<Empresa>> obtenerEmpresa(@PathVariable String ruc) {
        log.info("GET /empresa/empresas/{} - Obteniendo empresa", ruc);
        return empresaService.obtenerEmpresaPorId(ruc)
                .map(empresa -> ResponseEntity.ok(ApiResponse.success(empresa)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las empresas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Empresa>>> obtenerTodasEmpresas() {
        log.info("GET /empresa/empresas - Obteniendo todas las empresas");
        List<Empresa> empresas = empresaService.obtenerTodasLasEmpresas();
        return ResponseEntity.ok(ApiResponse.success(empresas));
    }

    @Operation(summary = "Actualizar una empresa existente")
    @PutMapping("/{ruc}")
    public ResponseEntity<ApiResponse<Empresa>> actualizarEmpresa(
            @PathVariable String ruc,
            @Valid @RequestBody Empresa empresa) {
        log.info("PUT /empresa/empresas/{} - Actualizando empresa", ruc);
        Empresa actualizada = empresaService.actualizarEmpresa(ruc, empresa);
        return ResponseEntity.ok(ApiResponse.success(actualizada, "Empresa actualizada exitosamente"));
    }

    @Operation(summary = "Eliminar una empresa por su RUC")
    @DeleteMapping("/{ruc}")
    public ResponseEntity<ApiResponse<Void>> eliminarEmpresa(@PathVariable String ruc) {
        log.info("DELETE /empresa/empresas/{} - Eliminando empresa", ruc);
        empresaService.eliminarEmpresa(ruc);
        return ResponseEntity.ok(ApiResponse.success(null, "Empresa eliminada exitosamente"));
    }

    @Operation(summary = "Buscar empresa por RUC")
    @GetMapping("/buscar/ruc/{ruc}")
    public ResponseEntity<ApiResponse<Empresa>> buscarPorRuc(@PathVariable String ruc) {
        log.info("GET /empresa/empresas/buscar/ruc/{} - Buscando por RUC", ruc);
        Empresa empresa = empresaService.buscarPorRuc(ruc);
        if (empresa != null) {
            return ResponseEntity.ok(ApiResponse.success(empresa));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar empresas por razon social que contenga")
    @GetMapping("/buscar/razon-social/{razonSocial}")
    public ResponseEntity<ApiResponse<List<Empresa>>> buscarPorRazonSocialContaining(@PathVariable String razonSocial) {
        log.info("GET /empresa/empresas/buscar/razon-social/{} - Buscando por razon social", razonSocial);
        List<Empresa> empresas = empresaService.buscarPorRazonSocialContaining(razonSocial);
        return ResponseEntity.ok(ApiResponse.success(empresas));
    }
}