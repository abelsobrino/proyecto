package pe.edu.utp.proyecto.service.finanzas_service;

import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoFinancieroService {

    // CRUD Básico
    MovimientoFinanciero guardarMovimiento(MovimientoFinanciero movimiento);
    Optional<MovimientoFinanciero> obtenerMovimientoPorId(Integer id);
    List<MovimientoFinanciero> obtenerTodosLosMovimientos();
    MovimientoFinanciero actualizarMovimiento(Integer id, MovimientoFinanciero movimiento);
    void eliminarMovimiento(Integer id);

    // Consultas específicas
    List<MovimientoFinanciero> obtenerMovimientosPorTipo(String tipo);
    List<MovimientoFinanciero> obtenerMovimientosPorFecha(LocalDate fecha);
}