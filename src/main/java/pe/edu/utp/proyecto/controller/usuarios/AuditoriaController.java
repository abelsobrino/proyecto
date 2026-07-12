package pe.edu.utp.proyecto.controller.usuarios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;
import pe.edu.utp.proyecto.service.usuarios.AuditoriaService;
import java.util.List;

@RestController
@RequestMapping("/usuarios/auditorias")
@Tag(name = "Auditorias", description = "Gestion de auditorias del sistema")
@RequiredArgsConstructor
@Slf4j
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @Operation(summary = "Obtener todas las auditorias")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Auditoria>>> obtenerTodasAuditorias() {
        log.info("GET /usuarios/auditorias - Obteniendo todas las auditorias");
        List<Auditoria> auditorias = auditoriaService.obtenerTodasLasAuditorias();
        return ResponseEntity.ok(ApiResponse.success(auditorias));
    }

    @Operation(summary = "Obtener una auditoria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Auditoria>> obtenerAuditoria(@PathVariable Integer id) {
        log.info("GET /usuarios/auditorias/{} - Obteniendo auditoria", id);
        return auditoriaService.obtenerAuditoriaPorId(id)
                .map(auditoria -> ResponseEntity.ok(ApiResponse.success(auditoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener auditorias por accion")
    @GetMapping("/accion/{accion}")
    public ResponseEntity<ApiResponse<List<Auditoria>>> obtenerPorAccion(@PathVariable String accion) {
        log.info("GET /usuarios/auditorias/accion/{} - Obteniendo auditorias por accion", accion);
        List<Auditoria> auditorias = auditoriaService.obtenerPorAccion(accion);
        return ResponseEntity.ok(ApiResponse.success(auditorias));
    }
}