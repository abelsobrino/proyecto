package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de clientes.
 * Define las operaciones disponibles para la entidad Cliente.
 */
public interface ClienteService {

    /**
     * Guarda un nuevo cliente en el sistema.
     * @param cliente Datos del cliente a guardar.
     * @return Cliente guardado con su ID generado.
     */
    Cliente guardarCliente(Cliente cliente);

    /**
     * Busca un cliente por su ID.
     * @param id ID del cliente.
     * @return Optional con el cliente encontrado o vacio.
     */
    Optional<Cliente> obtenerClientePorId(Integer id);

    /**
     * Obtiene todos los clientes registrados.
     * @return Lista de todos los clientes.
     */
    List<Cliente> obtenerTodosLosClientes();

    /**
     * Actualiza los datos de un cliente existente.
     * @param id ID del cliente a actualizar.
     * @param cliente Datos actualizados del cliente.
     * @return Cliente actualizado.
     */
    Cliente actualizarCliente(Integer id, Cliente cliente);

    /**
     * Elimina un cliente del sistema.
     * @param id ID del cliente a eliminar.
     */
    void eliminarCliente(Integer id);

    /**
     * Busca un cliente por su numero de documento.
     * @param documento Numero de documento del cliente.
     * @return Cliente encontrado o null.
     */
    Cliente buscarPorDocumento(String documento);

    /**
     * Busca clientes cuyo nombre contenga un texto.
     * @param nombres Texto a buscar en el nombre.
     * @return Lista de clientes que coinciden.
     */
    List<Cliente> buscarPorNombresContaining(String nombres);
}