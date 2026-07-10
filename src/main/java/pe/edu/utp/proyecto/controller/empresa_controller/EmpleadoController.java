package pe.edu.utp.proyecto.controller.empresa_controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.proyecto.dto.ApiResponse;
import pe.edu.utp.proyecto.modelo.empresa.Empleado;
import pe.edu.utp.proyecto.service.empresa_service.EmpleadoService;
import java.util.List;

@RestController
@RequestMapping("/empresa/empleados")
@Tag(name = "Empleados", description = "Gestion de empleados")
@RequiredArgsConstructor
@Slf4j
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Operation(summary = "Crear un nuevo empleado")
    @PostMapping
    public ResponseEntity<ApiResponse<Empleado>> crearEmpleado(@Valid @RequestBody Empleado empleado) {
        log.info("POST /empresa/empleados - Creando nuevo empleado");
        Empleado creado = empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Empleado creado exitosamente"));
    }

    @Operation(summary = "Obtener un empleado por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Empleado>> obtenerEmpleado(@PathVariable Integer id) {
        log.info("GET /empresa/empleados/{} - Obteniendo empleado", id);
        return empleadoService.obtenerEmpleadoPorId(id)
                .map(empleado -> ResponseEntity.ok(ApiResponse.success(empleado)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los empleados")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Empleado>>> obtenerTodosEmpleados() {
        log.info("GET /empresa/empleados - Obteniendo todos los empleados");
        List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();
        return ResponseEntity.ok(ApiResponse.success(empleados));
    }

    @Operation(summary = "Actualizar un empleado existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Empleado>> actualizarEmpleado(
            @PathVariable Integer id,
            @Valid @RequestBody Empleado empleado) {
        log.info("PUT /empresa/empleados/{} - Actualizando empleado", id);
        Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Empleado actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un empleado por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarEmpleado(@PathVariable Integer id) {
        log.info("DELETE /empresa/empleados/{} - Eliminando empleado", id);
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Empleado eliminado exitosamente"));
    }
}