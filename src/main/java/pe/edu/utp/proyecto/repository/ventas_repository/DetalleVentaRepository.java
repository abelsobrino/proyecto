package pe.edu.utp.proyecto.repository.ventas_repository;

import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

    List<DetalleVenta> findByProductoIdProducto(Integer idProducto);

    List<DetalleVenta> findBySubtotalBetween(Double min, Double max);
}