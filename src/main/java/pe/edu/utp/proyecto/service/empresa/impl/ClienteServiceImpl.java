package pe.edu.utp.proyecto.service.empresa.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import pe.edu.utp.proyecto.repository.empresa.ClienteRepository;
import pe.edu.utp.proyecto.service.empresa.ClienteService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de clientes.
 * Contiene la logica de negocio para la gestion de clientes.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    /**
     * Guarda un nuevo cliente.
     * @param cliente Datos del cliente.
     * @return Cliente guardado.
     */
    @Override
    @Transactional
    public Cliente guardarCliente(Cliente cliente) {
        try {
            log.info("Guardando nuevo cliente: {}", cliente.getNombres());
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            log.error("Error al guardar cliente: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el cliente: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente por su ID.
     * @param id ID del cliente.
     * @return Optional con el cliente encontrado.
     */
    @Override
    public Optional<Cliente> obtenerClientePorId(Integer id) {
        try {
            log.debug("Buscando cliente con ID: {}", id);
            return clienteRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar cliente: {}", e.getMessage());
            throw new BusinessException("Error al buscar el cliente: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes.
     * @return Lista de clientes.
     */
    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        try {
            log.info("Obteniendo todos los clientes");
            return clienteRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener clientes: {}", e.getMessage());
            throw new BusinessException("Error al obtener los clientes: " + e.getMessage());
        }
    }

    /**
     * Actualiza un cliente existente.
     * @param id ID del cliente.
     * @param cliente Datos actualizados.
     * @return Cliente actualizado.
     */
    @Override
    @Transactional
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        try {
            log.info("Actualizando cliente con ID: {}", id);
            Cliente existente = clienteRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Cliente no encontrado con ID: " + id));
            existente.setNombres(cliente.getNombres());
            existente.setDocumento(cliente.getDocumento());
            existente.setTelefono(cliente.getTelefono());
            return clienteRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar cliente: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el cliente: " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente.
     * @param id ID del cliente a eliminar.
     */
    @Override
    @Transactional
    public void eliminarCliente(Integer id) {
        try {
            log.info("Eliminando cliente con ID: {}", id);
            if (!clienteRepository.existsById(id)) {
                throw new BusinessException("Cliente no encontrado con ID: " + id);
            }
            clienteRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar cliente: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el cliente: " + e.getMessage());
        }
    }

    /**
     * Busca un cliente por su documento.
     * @param documento Numero de documento del cliente.
     * @return Cliente encontrado o null.
     */
    @Override
    public Cliente buscarPorDocumento(String documento) {
        try {
            log.info("Buscando cliente por documento: {}", documento);
            return clienteRepository.findByDocumento(documento);
        } catch (Exception e) {
            log.error("Error al buscar cliente por documento: {}", e.getMessage());
            throw new BusinessException("Error al buscar el cliente: " + e.getMessage());
        }
    }

    /**
     * Busca clientes cuyo nombre contenga un texto.
     * @param nombres Texto a buscar en el nombre.
     * @return Lista de clientes.
     */
    @Override
    public List<Cliente> buscarPorNombresContaining(String nombres) {
        try {
            log.info("Buscando clientes por nombre que contenga: {}", nombres);
            return clienteRepository.findByNombresContaining(nombres);
        } catch (Exception e) {
            log.error("Error al buscar clientes por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar clientes: " + e.getMessage());
        }
    }
}