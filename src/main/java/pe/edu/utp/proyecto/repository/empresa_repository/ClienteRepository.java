package pe.edu.utp.proyecto.repository.empresa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Cliente;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByDocumento(String documento);
    List<Cliente> findByNombresContaining(String nombres);
}