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

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

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
}