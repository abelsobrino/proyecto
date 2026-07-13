package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

/**
 * Repositorio para la entidad Venta.
 * Metodos CRUD heredados de JpaRepository.
 */
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    // Metodos CRUD heredados de JpaRepository
}