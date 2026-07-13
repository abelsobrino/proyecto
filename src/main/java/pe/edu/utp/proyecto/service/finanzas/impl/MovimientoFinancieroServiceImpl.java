package pe.edu.utp.proyecto.service.finanzas.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;
import pe.edu.utp.proyecto.repository.finanzas.MovimientoFinancieroRepository;
import pe.edu.utp.proyecto.service.finanzas.MovimientoFinancieroService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de movimientos financieros.
 * Contiene la logica de negocio para la gestion de movimientos financieros.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientoFinancieroServiceImpl implements MovimientoFinancieroService {

    private final MovimientoFinancieroRepository movimientoFinancieroRepository;

    /**
     * Guarda un nuevo movimiento financiero.
     * @param movimiento Datos del movimiento.
     * @return Movimiento guardado.
     */
    @Override
    @Transactional
    public MovimientoFinanciero guardarMovimiento(MovimientoFinanciero movimiento) {
        try {
            log.info("Guardando nuevo movimiento financiero: {}", movimiento.getTipo());
            movimiento.registrarMovimiento();
            return movimientoFinancieroRepository.save(movimiento);
        } catch (Exception e) {
            log.error("Error al guardar movimiento financiero: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el movimiento financiero: " + e.getMessage());
        }
    }

    /**
     * Busca un movimiento financiero por su ID.
     * @param id ID del movimiento.
     * @return Optional con el movimiento encontrado.
     */
    @Override
    public Optional<MovimientoFinanciero> obtenerMovimientoPorId(Integer id) {
        try {
            log.debug("Buscando movimiento con ID: {}", id);
            return movimientoFinancieroRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar movimiento: {}", e.getMessage());
            throw new BusinessException("Error al buscar el movimiento: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los movimientos financieros.
     * @return Lista de movimientos.
     */
    @Override
    public List<MovimientoFinanciero> obtenerTodosLosMovimientos() {
        try {
            log.info("Obteniendo todos los movimientos financieros");
            return movimientoFinancieroRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener movimientos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los movimientos: " + e.getMessage());
        }
    }

    /**
     * Actualiza un movimiento financiero existente.
     * @param id ID del movimiento.
     * @param movimiento Datos actualizados.
     * @return Movimiento actualizado.
     */
    @Override
    @Transactional
    public MovimientoFinanciero actualizarMovimiento(Integer id, MovimientoFinanciero movimiento) {
        try {
            log.info("Actualizando movimiento con ID: {}", id);
            MovimientoFinanciero existente = movimientoFinancieroRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Movimiento no encontrado con ID: " + id));
            existente.setTipo(movimiento.getTipo());
            existente.setMonto(movimiento.getMonto());
            existente.setFecha(movimiento.getFecha());
            return movimientoFinancieroRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar movimiento: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el movimiento: " + e.getMessage());
        }
    }

    /**
     * Elimina un movimiento financiero.
     * @param id ID del movimiento a eliminar.
     */
    @Override
    @Transactional
    public void eliminarMovimiento(Integer id) {
        try {
            log.info("Eliminando movimiento con ID: {}", id);
            if (!movimientoFinancieroRepository.existsById(id)) {
                throw new BusinessException("Movimiento no encontrado con ID: " + id);
            }
            movimientoFinancieroRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar movimiento: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el movimiento: " + e.getMessage());
        }
    }

    /**
     * Busca movimientos financieros por tipo.
     * @param tipo Tipo de movimiento.
     * @return Lista de movimientos.
     */
    @Override
    public List<MovimientoFinanciero> obtenerMovimientosPorTipo(String tipo) {
        try {
            log.info("Buscando movimientos por tipo: {}", tipo);
            return movimientoFinancieroRepository.findByTipo(tipo);
        } catch (Exception e) {
            log.error("Error al buscar movimientos por tipo: {}", e.getMessage());
            throw new BusinessException("Error al buscar los movimientos: " + e.getMessage());
        }
    }

    /**
     * Busca movimientos financieros por fecha.
     * @param fecha Fecha del movimiento.
     * @return Lista de movimientos.
     */
    @Override
    public List<MovimientoFinanciero> obtenerMovimientosPorFecha(LocalDate fecha) {
        try {
            log.info("Buscando movimientos por fecha: {}", fecha);
            return movimientoFinancieroRepository.findByFecha(fecha);
        } catch (Exception e) {
            log.error("Error al buscar movimientos por fecha: {}", e.getMessage());
            throw new BusinessException("Error al buscar los movimientos: " + e.getMessage());
        }
    }
}