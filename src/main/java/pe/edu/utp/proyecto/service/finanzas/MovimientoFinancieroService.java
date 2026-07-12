package pe.edu.utp.proyecto.service.finanzas;

import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoFinancieroService {
    MovimientoFinanciero guardarMovimiento(MovimientoFinanciero movimiento);
    Optional<MovimientoFinanciero> obtenerMovimientoPorId(Integer id);
    List<MovimientoFinanciero> obtenerTodosLosMovimientos();
    MovimientoFinanciero actualizarMovimiento(Integer id, MovimientoFinanciero movimiento);
    void eliminarMovimiento(Integer id);
    List<MovimientoFinanciero> obtenerMovimientosPorTipo(String tipo);
    List<MovimientoFinanciero> obtenerMovimientosPorFecha(LocalDate fecha);
}