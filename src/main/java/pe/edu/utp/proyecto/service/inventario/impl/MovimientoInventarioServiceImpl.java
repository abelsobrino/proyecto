package pe.edu.utp.proyecto.service.inventario.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;
import pe.edu.utp.proyecto.repository.inventario.MovimientoInventarioRepository;
import pe.edu.utp.proyecto.service.inventario.MovimientoInventarioService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de movimientos de inventario.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;

    @Override
    @Transactional
    public MovimientoInventario guardarMovimiento(MovimientoInventario movimiento) {
        try {
            log.info("Guardando movimiento de tipo: {}", movimiento.getTipoMovimiento());
            movimiento.registrarMovimiento();
            return movimientoRepository.save(movimiento);
        } catch (Exception e) {
            log.error("Error al guardar movimiento de inventario: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el movimiento: " + e.getMessage());
        }
    }

    @Override
    public Optional<MovimientoInventario> obtenerMovimientoPorId(Integer id) {
        try {
            log.debug("Buscando movimiento con ID: {}", id);
            return movimientoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar movimiento: {}", e.getMessage());
            throw new BusinessException("Error al buscar el movimiento: " + e.getMessage());
        }
    }

    @Override
    public List<MovimientoInventario> obtenerTodosLosMovimientos() {
        try {
            log.info("Obteniendo todos los movimientos de inventario");
            return movimientoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener movimientos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los movimientos: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public MovimientoInventario actualizarMovimiento(Integer id, MovimientoInventario movimiento) {
        try {
            log.info("Actualizando movimiento con ID: {}", id);
            MovimientoInventario existente = movimientoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Movimiento no encontrado con ID: " + id));
            existente.setTipoMovimiento(movimiento.getTipoMovimiento());
            existente.setCantidad(movimiento.getCantidad());
            existente.setFechaMovimiento(movimiento.getFechaMovimiento());
            existente.setMotivo(movimiento.getMotivo());
            existente.setProducto(movimiento.getProducto());
            return movimientoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar movimiento: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el movimiento: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarMovimiento(Integer id) {
        try {
            log.info("Eliminando movimiento con ID: {}", id);
            if (!movimientoRepository.existsById(id)) {
                throw new BusinessException("Movimiento no encontrado con ID: " + id);
            }
            movimientoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar movimiento: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el movimiento: " + e.getMessage());
        }
    }

    @Override
    public List<MovimientoInventario> buscarPorTipo(String tipoMovimiento) {
        try {
            log.info("Buscando movimientos de tipo: {}", tipoMovimiento);
            return movimientoRepository.findByTipoMovimiento(tipoMovimiento);
        } catch (Exception e) {
            log.error("Error al buscar movimientos por tipo: {}", e.getMessage());
            throw new BusinessException("Error al buscar movimientos: " + e.getMessage());
        }
    }

    @Override
    public List<MovimientoInventario> buscarPorProducto(Integer idProducto) {
        try {
            log.info("Buscando movimientos del producto ID: {}", idProducto);
            return movimientoRepository.findByProductoIdProducto(idProducto);
        } catch (Exception e) {
            log.error("Error al buscar movimientos por producto: {}", e.getMessage());
            throw new BusinessException("Error al buscar movimientos: " + e.getMessage());
        }
    }

    @Override
    public List<MovimientoInventario> buscarEntreFechas(Date inicio, Date fin) {
        try {
            log.info("Buscando movimientos entre {} y {}", inicio, fin);
            return movimientoRepository.findByFechaMovimientoBetween(inicio, fin);
        } catch (Exception e) {
            log.error("Error al buscar movimientos entre fechas: {}", e.getMessage());
            throw new BusinessException("Error al buscar movimientos: " + e.getMessage());
        }
    }
}