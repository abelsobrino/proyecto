package pe.edu.utp.proyecto.service.impl.empresa_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import pe.edu.utp.proyecto.repository.empresa_repository.ClienteRepository;
import pe.edu.utp.proyecto.service.empresa_service.ClienteService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente guardarCliente(Cliente cliente) {
        log.info("Guardando nuevo cliente");
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Integer id) {
        log.debug("Buscando cliente con ID: {}", id);
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        log.info("Obteniendo todos los clientes");
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Cliente actualizarCliente(Integer id, Cliente cliente) {
        log.info("Actualizando cliente con ID: {}", id);

        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente no encontrado con ID: " + id));

        existente.setNombres(cliente.getNombres());
        existente.setDocumento(cliente.getDocumento());
        existente.setTelefono(cliente.getTelefono());

        return clienteRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Integer id) {
        log.info("Eliminando cliente con ID: {}", id);
        clienteRepository.deleteById(id);
    }
}