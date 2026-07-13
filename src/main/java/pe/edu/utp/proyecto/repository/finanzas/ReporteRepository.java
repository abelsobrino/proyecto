package pe.edu.utp.proyecto.repository.finanzas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.finanzas.Reporte;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio para la entidad Reporte.
 */
@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    /**
     * Busca reportes por tipo de reporte.
     * @param tipoReporte Tipo de reporte.
     * @return Lista de reportes del tipo especificado.
     */
    List<Reporte> findByTipoReporte(String tipoReporte);

    /**
     * Busca reportes por fecha de generacion.
     * @param fechaGeneracion Fecha en que se genero el reporte.
     * @return Lista de reportes de esa fecha.
     */
    List<Reporte> findByFechaGeneracion(LocalDate fechaGeneracion);
}