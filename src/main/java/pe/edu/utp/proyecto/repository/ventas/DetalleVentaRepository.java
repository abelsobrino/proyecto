package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repositorio para la entidad DetalleVenta.
 */
@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    /**
     * Busca detalles de venta de un producto especifico.
     * @param idProducto ID del producto.
     * @return Lista de detalles del producto.
     */
    List<DetalleVenta> findByProductoIdProducto(Integer idProducto);

    /**
     * Busca detalles de venta cuyo subtotal este en un rango.
     * @param min Valor minimo del subtotal.
     * @param max Valor maximo del subtotal.
     * @return Lista de detalles en el rango.
     */
    List<DetalleVenta> findBySubtotalBetween(BigDecimal min, BigDecimal max);
}