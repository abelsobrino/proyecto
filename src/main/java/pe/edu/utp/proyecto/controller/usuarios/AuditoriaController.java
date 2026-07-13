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

/**
 * Controlador REST para la gestion de auditorias.
 * Las auditorias son registros de solo lectura.
 */
@RestController
@RequestMapping("/usuarios/auditorias")
@Tag(name = "Auditorias", description = "Gestion de auditorias del sistema")
@RequiredArgsConstructor
@Slf4j
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    /**
     * Lista todas las auditorias.
     * @return Lista de auditorias.
     */
    @Operation(summary = "Obtener todas las auditorias")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Auditoria>>> obtenerTodasAuditorias() {
        log.info("GET /usuarios/auditorias - Obteniendo todas las auditorias");
        List<Auditoria> auditorias = auditoriaService.obtenerTodasLasAuditorias();
        return ResponseEntity.ok(ApiResponse.success(auditorias));
    }

    /**
     * Obtiene una auditoria por su ID.
     * @param id ID de la auditoria.
     * @return Auditoria encontrada o 404.
     */
    @Operation(summary = "Obtener una auditoria por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Auditoria>> obtenerAuditoria(@PathVariable Integer id) {
        log.info("GET /usuarios/auditorias/{} - Obteniendo auditoria", id);
        return auditoriaService.obtenerAuditoriaPorId(id)
                .map(auditoria -> ResponseEntity.ok(ApiResponse.success(auditoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca auditorias por accion.
     * @param accion Accion realizada.
     * @return Lista de auditorias.
     */
    @Operation(summary = "Buscar auditorias por accion")
    @GetMapping("/accion/{accion}")
    public ResponseEntity<ApiResponse<List<Auditoria>>> obtenerPorAccion(@PathVariable String accion) {
        log.info("GET /usuarios/auditorias/accion/{} - Buscando por accion", accion);
        List<Auditoria> auditorias = auditoriaService.obtenerPorAccion(accion);
        return ResponseEntity.ok(ApiResponse.success(auditorias));
    }

    /**
     * Busca auditorias por texto en descripcion.
     * @param texto Texto a buscar.
     * @return Lista de auditorias.
     */
    @Operation(summary = "Buscar auditorias por texto en descripcion")
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<Auditoria>>> buscarEnDescripcion(@RequestParam String texto) {
        log.info("GET /usuarios/auditorias/buscar - Buscando auditorias con texto: {}", texto);
        List<Auditoria> auditorias = auditoriaService.buscarEnDescripcion(texto);
        return ResponseEntity.ok(ApiResponse.success(auditorias));
    }
}