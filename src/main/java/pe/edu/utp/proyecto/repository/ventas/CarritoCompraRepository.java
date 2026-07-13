package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;

import java.util.List;

/**
 * Repositorio para la entidad CarritoCompra.
 */
@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Integer> {

    /**
     * Busca carritos de compra por su estado.
     * @param estado Estado del carrito (ACTIVO, CERRADO, ABANDONADO).
     * @return Lista de carritos con ese estado.
     */
    List<CarritoCompra> findByEstado(String estado);
}