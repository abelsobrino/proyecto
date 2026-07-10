package pe.edu.utp.proyecto.service.empresa_service;

import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);
    Optional<Cliente> obtenerClientePorId(Integer id);
    List<Cliente> obtenerTodosLosClientes();
    Cliente actualizarCliente(Integer id, Cliente cliente);
    void eliminarCliente(Integer id);
}