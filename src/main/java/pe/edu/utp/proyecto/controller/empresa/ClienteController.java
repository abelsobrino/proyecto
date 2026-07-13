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
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import pe.edu.utp.proyecto.service.empresa.ClienteService;

import java.util.List;

/**
 * Controlador REST para la gestion de clientes.
 * Expone endpoints para operaciones CRUD y consultas especializadas.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@RequestMapping("/empresa/clientes")
@Tag(name = "Clientes", description = "Gestion de clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * Crea un nuevo cliente en el sistema.
     * @param cliente Datos del cliente a crear.
     * @return Cliente creado con su ID generado.
     */
    @Operation(summary = "Crear un nuevo cliente")
    @PostMapping
    public ResponseEntity<ApiResponse<Cliente>> crearCliente(@Valid @RequestBody Cliente cliente) {
        log.info("POST /empresa/clientes - Creando nuevo cliente");
        Cliente creado = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(creado, "Cliente creado exitosamente"));
    }

    /**
     * Obtiene un cliente por su ID.
     * @param id ID del cliente.
     * @return Cliente encontrado o 404 Not Found.
     */
    @Operation(summary = "Obtener un cliente por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> obtenerCliente(@PathVariable Integer id) {
        log.info("GET /empresa/clientes/{} - Obteniendo cliente", id);
        return clienteService.obtenerClientePorId(id)
                .map(cliente -> ResponseEntity.ok(ApiResponse.success(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lista todos los clientes registrados.
     * @return Lista de clientes.
     */
    @Operation(summary = "Obtener todos los clientes")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Cliente>>> obtenerTodosClientes() {
        log.info("GET /empresa/clientes - Obteniendo todos los clientes");
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(ApiResponse.success(clientes));
    }

    /**
     * Actualiza un cliente existente.
     * @param id ID del cliente a actualizar.
     * @param cliente Datos actualizados.
     * @return Cliente actualizado.
     */
    @Operation(summary = "Actualizar un cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> actualizarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {
        log.info("PUT /empresa/clientes/{} - Actualizando cliente", id);
        Cliente actualizado = clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok(ApiResponse.success(actualizado, "Cliente actualizado exitosamente"));
    }

    /**
     * Elimina un cliente por su ID.
     * @param id ID del cliente a eliminar.
     */
    @Operation(summary = "Eliminar un cliente por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCliente(@PathVariable Integer id) {
        log.info("DELETE /empresa/clientes/{} - Eliminando cliente", id);
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Cliente eliminado exitosamente"));
    }

    /**
     * Busca un cliente por su documento.
     * @param documento Numero de documento del cliente.
     * @return Cliente encontrado o 404 Not Found.
     */
    @Operation(summary = "Buscar cliente por documento")
    @GetMapping("/documento/{documento}")
    public ResponseEntity<ApiResponse<Cliente>> buscarPorDocumento(@PathVariable String documento) {
        log.info("GET /empresa/clientes/documento/{} - Buscando por documento", documento);
        Cliente cliente = clienteService.buscarPorDocumento(documento);
        if (cliente != null) {
            return ResponseEntity.ok(ApiResponse.success(cliente));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Busca clientes cuyo nombre contenga un texto.
     * @param nombres Texto a buscar en el nombre.
     * @return Lista de clientes que coinciden.
     */
    @Operation(summary = "Buscar clientes por nombre que contenga")
    @GetMapping("/nombre/{nombres}")
    public ResponseEntity<ApiResponse<List<Cliente>>> buscarPorNombresContaining(@PathVariable String nombres) {
        log.info("GET /empresa/clientes/nombre/{} - Buscando por nombre que contenga", nombres);
        List<Cliente> clientes = clienteService.buscarPorNombresContaining(nombres);
        return ResponseEntity.ok(ApiResponse.success(clientes));
    }
}