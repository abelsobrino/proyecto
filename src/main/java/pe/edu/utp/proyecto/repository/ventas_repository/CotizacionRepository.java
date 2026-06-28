package pe.edu.utp.proyecto.repository.ventas_repository;

import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {

    List<Cotizacion> findByFechaBetween(Date fechaInicio, Date fechaFin);

    List<Cotizacion> findByTotalEstimadoGreaterThan(double total);

    List<Cotizacion> findTop5ByOrderByFechaDesc();
}