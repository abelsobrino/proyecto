package pe.edu.utp.proyecto.repository.ventas;

import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    Optional<MetodoPago> findByNombreMetodo(String nombreMetodo);
}