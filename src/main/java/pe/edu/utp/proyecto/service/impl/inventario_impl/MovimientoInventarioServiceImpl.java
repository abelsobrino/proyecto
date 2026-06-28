package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;
import pe.edu.utp.proyecto.repository.inventario_repository.MovimientoInventarioRepository;
import pe.edu.utp.proyecto.service.inventario_service.MovimientoInventarioService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoRepository;

    @Override
    @Transactional
    public MovimientoInventario guardarMovimiento(MovimientoInventario movimiento) {
        log.info("Guardando movimiento de tipo: {}", movimiento.getTipoMovimiento());
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Optional<MovimientoInventario> obtenerMovimientoPorId(Integer id) {
        log.debug("Buscando movimiento con ID: {}", id);
        return movimientoRepository.findById(id);
    }

    @Override
    public List<MovimientoInventario> obtenerTodosLosMovimientos() {
        log.info("Obteniendo todos los movimientos de inventario");
        return movimientoRepository.findAll();
    }

    @Override
    @Transactional
    public MovimientoInventario actualizarMovimiento(Integer id, MovimientoInventario movimiento) {
        log.info("Actualizando movimiento con ID: {}", id);
        MovimientoInventario existente = movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Movimiento no encontrado con ID: " + id));
        existente.setTipoMovimiento(movimiento.getTipoMovimiento());
        existente.setCantidad(movimiento.getCantidad());
        existente.setFechaMovimiento(movimiento.getFechaMovimiento());
        existente.setMotivo(movimiento.getMotivo());
        existente.setProducto(movimiento.getProducto());
        return movimientoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarMovimiento(Integer id) {
        log.info("Eliminando movimiento con ID: {}", id);
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<MovimientoInventario> obtenerPorTipo(String tipoMovimiento) {
        log.info("Obteniendo movimientos de tipo: {}", tipoMovimiento);
        return movimientoRepository.findByTipoMovimiento(tipoMovimiento);
    }

    @Override
    public List<MovimientoInventario> obtenerPorProducto(Integer idProducto) {
        log.info("Obteniendo movimientos del producto ID: {}", idProducto);
        return movimientoRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    public List<MovimientoInventario> obtenerEntreFechas(Date inicio, Date fin) {
        log.info("Obteniendo movimientos entre {} y {}", inicio, fin);
        return movimientoRepository.findByFechaMovimientoBetween(inicio, fin);
    }
}
