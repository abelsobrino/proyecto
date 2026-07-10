package pe.edu.utp.proyecto.repository.finanzas_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoFinancieroRepository extends JpaRepository<MovimientoFinanciero, Integer> {
    List<MovimientoFinanciero> findByTipo(String tipo);
    List<MovimientoFinanciero> findByFecha(LocalDate fecha);
}