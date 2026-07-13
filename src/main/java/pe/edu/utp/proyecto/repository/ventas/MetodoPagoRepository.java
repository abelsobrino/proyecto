package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;

import java.util.Optional;

/**
 * Repositorio para la entidad MetodoPago.
 */
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

    /**
     * Busca un metodo de pago por su nombre.
     * @param nombreMetodo Nombre del metodo de pago.
     * @return Optional con el metodo encontrado o vacio.
     */
    Optional<MetodoPago> findByNombreMetodo(String nombreMetodo);
}