package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para la entidad MovimientoInventario.
 */
@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {

    /**
     * Busca movimientos de inventario por tipo (ENTRADA/SALIDA).
     * @param tipoMovimiento Tipo de movimiento.
     * @return Lista de movimientos del tipo especificado.
     */
    List<MovimientoInventario> findByTipoMovimiento(String tipoMovimiento);

    /**
     * Busca movimientos de inventario de un producto especifico.
     * @param idProducto ID del producto.
     * @return Lista de movimientos del producto.
     */
    List<MovimientoInventario> findByProductoIdProducto(Integer idProducto);

    /**
     * Busca movimientos de inventario entre dos fechas.
     * @param inicio Fecha de inicio del rango.
     * @param fin Fecha de fin del rango.
     * @return Lista de movimientos en el rango de fechas.
     */
    List<MovimientoInventario> findByFechaMovimientoBetween(Date inicio, Date fin);
}