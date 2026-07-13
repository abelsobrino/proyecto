package pe.edu.utp.proyecto.repository.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;

import java.util.List;

/**
 * Repositorio para la entidad Cliente.
 * Proporciona metodos CRUD y consultas personalizadas.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Busca un cliente por su numero de documento.
     * @param documento Numero de documento del cliente.
     * @return Cliente encontrado o null.
     */
    Cliente findByDocumento(String documento);

    /**
     * Busca clientes cuyo nombre contenga un texto.
     * @param nombres Texto a buscar en el nombre.
     * @return Lista de clientes que coinciden.
     */
    List<Cliente> findByNombresContaining(String nombres);
}