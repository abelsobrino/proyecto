package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;

import java.util.Date;
import java.util.List;

/**
 * Repositorio para la entidad Cotizacion.
 */
@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {

    /**
     * Busca cotizaciones entre dos fechas.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Lista de cotizaciones en el rango.
     */
    List<Cotizacion> findByFechaBetween(Date fechaInicio, Date fechaFin);

    /**
     * Busca cotizaciones cuyo total estimado sea mayor a un valor.
     * @param total Valor minimo del total estimado.
     * @return Lista de cotizaciones con total mayor al especificado.
     */
    List<Cotizacion> findByTotalEstimadoGreaterThan(double total);

    /**
     * Obtiene las 5 cotizaciones mas recientes.
     * @return Lista de las 5 cotizaciones mas recientes.
     */
    List<Cotizacion> findTop5ByOrderByFechaDesc();
}