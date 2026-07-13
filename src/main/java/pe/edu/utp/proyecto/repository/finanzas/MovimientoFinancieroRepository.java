package pe.edu.utp.proyecto.repository.finanzas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio para la entidad MovimientoFinanciero.
 */
@Repository
public interface MovimientoFinancieroRepository extends JpaRepository<MovimientoFinanciero, Integer> {

    /**
     * Busca movimientos financieros por tipo.
     * @param tipo Tipo de movimiento (INGRESO/EGRESO).
     * @return Lista de movimientos del tipo especificado.
     */
    List<MovimientoFinanciero> findByTipo(String tipo);

    /**
     * Busca movimientos financieros por fecha.
     * @param fecha Fecha del movimiento.
     * @return Lista de movimientos de esa fecha.
     */
    List<MovimientoFinanciero> findByFecha(LocalDate fecha);
}