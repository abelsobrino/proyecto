package pe.edu.utp.proyecto.repository.finanzas_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.finanzas.Reporte;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    List<Reporte> findByTipoReporte(String tipoReporte);

    List<Reporte> findByFechaGeneracion(LocalDate fechaGeneracion);
}