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
import pe.edu.utp.proyecto.modelo.empresa.Empleado;
import pe.edu.utp.proyecto.service.empresa.EmpleadoService;

import java.util.List;

/**
 * Controlador REST para la gestion de empleados.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/empresa/empleados")
@Tag(name = "Empleados", description = "Gestion de empleados")
@RequiredArgsConstructor
@Slf4j
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    /**
     * Crea un nuevo empleado en el sistema.
     * @param empleado Datos del empleado a crear.
     * @return Empleado creado con su ID generado.
     */
    @Operation(summary = "Crear un nuevo empleado")
    @PostMapping
    public ResponseEntity<ApiResponse<Empleado>> crearEmpleado(@Valid @RequestBody Empleado empleado) {
        log.info("POST /empresa/empleados - Creando nuevo empleado");
        Empleado creado = empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Empleado creado exitosamente"));
    }

    /**
     * Obtiene un empleado por su ID.
     * @param id ID del empleado.
     * @return Empleado encontrado o 404 Not Found.
     */
    @Operation(summary = "Obtener un empleado por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Empleado>> obtenerEmpleado(@PathVariable Integer id) {
        log.info("GET /empresa/empleados/{} - Obteniendo empleado", id);
        return empleadoService.obtenerEmpleadoPorId(id)
                .map(empleado -> ResponseEntity.ok(ApiResponse.success(empleado)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los empleados registrados.
     * @return Lista de empleados.
     */
    @Operation(summary = "Obtener todos los empleados")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Empleado>>> obtenerTodosEmpleados() {
        log.info("GET /empresa/empleados - Obteniendo todos los empleados");
        List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();
        return ResponseEntity.ok(ApiResponse.success(empleados));
    }

    /**
     * Actualiza un empleado existente.
     * @param id ID del empleado a actualizar.
     * @param empleado Datos actualizados.
     * @return Empleado actualizado.
     */
    @Operation(summary = "Actualizar un empleado existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Empleado>> actualizarEmpleado(
            @PathVariable Integer id,
            @Valid @RequestBody Empleado empleado) {
        log.info("PUT /empresa/empleados/{} - Actualizando empleado", id);
        Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Empleado actualizado exitosamente"));
    }

    /**
     * Elimina un empleado por su ID.
     * @param id ID del empleado a eliminar.
     */
    @Operation(summary = "Eliminar un empleado por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarEmpleado(@PathVariable Integer id) {
        log.info("DELETE /empresa/empleados/{} - Eliminando empleado", id);
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Empleado eliminado exitosamente"));
    }

    /**
     * Busca empleados por cargo.
     * @param cargo Cargo del empleado.
     * @return Lista de empleados con ese cargo.
     */
    @Operation(summary = "Buscar empleados por cargo")
    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<ApiResponse<List<Empleado>>> buscarPorCargo(@PathVariable String cargo) {
        log.info("GET /empresa/empleados/cargo/{} - Buscando por cargo", cargo);
        List<Empleado> empleados = empleadoService.buscarPorCargo(cargo);
        return ResponseEntity.ok(ApiResponse.success(empleados));
    }

    /**
     * Busca empleados cuyo apellido contenga un texto.
     * @param apellidos Texto a buscar en los apellidos.
     * @return Lista de empleados que coinciden.
     */
    @Operation(summary = "Buscar empleados por apellido que contenga")
    @GetMapping("/apellido/{apellidos}")
    public ResponseEntity<ApiResponse<List<Empleado>>> buscarPorApellidosContaining(@PathVariable String apellidos) {
        log.info("GET /empresa/empleados/apellido/{} - Buscando por apellido que contenga", apellidos);
        List<Empleado> empleados = empleadoService.buscarPorApellidosContaining(apellidos);
        return ResponseEntity.ok(ApiResponse.success(empleados));
    }
}