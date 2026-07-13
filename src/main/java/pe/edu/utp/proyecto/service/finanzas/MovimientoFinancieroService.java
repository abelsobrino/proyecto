package pe.edu.utp.proyecto.service.finanzas;

import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de movimientos financieros.
 * Define las operaciones disponibles para la entidad MovimientoFinanciero.
 */
public interface MovimientoFinancieroService {

    /**
     * Guarda un nuevo movimiento financiero en el sistema.
     * @param movimiento Datos del movimiento a guardar.
     * @return Movimiento guardado con su ID generado.
     */
    MovimientoFinanciero guardarMovimiento(MovimientoFinanciero movimiento);

    /**
     * Busca un movimiento financiero por su ID.
     * @param id ID del movimiento.
     * @return Optional con el movimiento encontrado o vacio.
     */
    Optional<MovimientoFinanciero> obtenerMovimientoPorId(Integer id);

    /**
     * Obtiene todos los movimientos financieros registrados.
     * @return Lista de todos los movimientos.
     */
    List<MovimientoFinanciero> obtenerTodosLosMovimientos();

    /**
     * Actualiza los datos de un movimiento financiero existente.
     * @param id ID del movimiento a actualizar.
     * @param movimiento Datos actualizados del movimiento.
     * @return Movimiento actualizado.
     */
    MovimientoFinanciero actualizarMovimiento(Integer id, MovimientoFinanciero movimiento);

    /**
     * Elimina un movimiento financiero del sistema.
     * @param id ID del movimiento a eliminar.
     */
    void eliminarMovimiento(Integer id);

    /**
     * Busca movimientos financieros por tipo.
     * @param tipo Tipo de movimiento (INGRESO/EGRESO).
     * @return Lista de movimientos del tipo especificado.
     */
    List<MovimientoFinanciero> obtenerMovimientosPorTipo(String tipo);

    /**
     * Busca movimientos financieros por fecha.
     * @param fecha Fecha del movimiento.
     * @return Lista de movimientos de esa fecha.
     */
    List<MovimientoFinanciero> obtenerMovimientosPorFecha(LocalDate fecha);
}