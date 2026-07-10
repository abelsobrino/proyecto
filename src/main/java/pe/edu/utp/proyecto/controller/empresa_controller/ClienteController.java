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
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import pe.edu.utp.proyecto.service.empresa_service.ClienteService;
import java.util.List;

@RestController
@RequestMapping("/empresa/clientes")
@Tag(name = "Clientes", description = "Gestion de clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Crear un nuevo cliente")
    @PostMapping
    public ResponseEntity<ApiResponse<Cliente>> crearCliente(@Valid @RequestBody Cliente cliente) {
        log.info("POST /empresa/clientes - Creando nuevo cliente");
        Cliente creado = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Cliente creado exitosamente"));
    }

    @Operation(summary = "Obtener un cliente por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> obtenerCliente(@PathVariable Integer id) {
        log.info("GET /empresa/clientes/{} - Obteniendo cliente", id);
        return clienteService.obtenerClientePorId(id)
                .map(cliente -> ResponseEntity.ok(ApiResponse.success(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los clientes")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Cliente>>> obtenerTodosClientes() {
        log.info("GET /empresa/clientes - Obteniendo todos los clientes");
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(ApiResponse.success(clientes));
    }

    @Operation(summary = "Actualizar un cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> actualizarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {
        log.info("PUT /empresa/clientes/{} - Actualizando cliente", id);
        Cliente actualizado = clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Cliente actualizado exitosamente"));
    }

    @Operation(summary = "Eliminar un cliente por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCliente(@PathVariable Integer id) {
        log.info("DELETE /empresa/clientes/{} - Eliminando cliente", id);
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Cliente eliminado exitosamente"));
    }
}