package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;
import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    List<MovimientoInventario> findByTipoMovimiento(String tipoMovimiento);
    List<MovimientoInventario> findByProductoIdProducto(Integer idProducto);
    List<MovimientoInventario> findByFechaMovimientoBetween(Date inicio, Date fin);
}