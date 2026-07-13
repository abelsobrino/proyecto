package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto.modelo.ventas.Pago;

/**
 * Repositorio para la entidad Pago.
 * Metodos CRUD heredados de JpaRepository.
 */
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // Metodos CRUD heredados de JpaRepository
}