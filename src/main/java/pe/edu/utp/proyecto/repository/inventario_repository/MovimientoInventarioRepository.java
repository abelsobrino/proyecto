package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {

    // Buscar por tipo ENTRADA o SALIDA
    List<MovimientoInventario> findByTipoMovimiento(String tipoMovimiento);

    // Buscar movimientos de un producto específico
    List<MovimientoInventario> findByProductoIdProducto(Integer idProducto);

    // Buscar movimientos entre fechas
    List<MovimientoInventario> findByFechaMovimientoBetween(Date inicio, Date fin);
}