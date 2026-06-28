package pe.edu.utp.proyecto.service.impl.finanzas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.finanzas.MovimientoFinanciero;
import pe.edu.utp.proyecto.repository.finanzas_repository.MovimientoFinancieroRepository;
import pe.edu.utp.proyecto.service.finanzas_service.MovimientoFinancieroService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MovimientoFinancieroServiceImpl implements MovimientoFinancieroService {

    @Autowired
    private MovimientoFinancieroRepository movimientoFinancieroRepository;

    @Override
    @Transactional
    public MovimientoFinanciero guardarMovimiento(MovimientoFinanciero movimiento) {
        log.info("Guardando nuevo movimiento financiero");
        return movimientoFinancieroRepository.save(movimiento);
    }

    @Override
    public Optional<MovimientoFinanciero> obtenerMovimientoPorId(Integer id) {
        log.debug("Buscando movimiento con ID: {}", id);
        return movimientoFinancieroRepository.findById(id);
    }

    @Override
    public List<MovimientoFinanciero> obtenerTodosLosMovimientos() {
        log.info("Obteniendo todos los movimientos financieros");
        return movimientoFinancieroRepository.findAll();
    }

    @Override
    @Transactional
    public MovimientoFinanciero actualizarMovimiento(Integer id, MovimientoFinanciero movimiento) {
        log.info("Actualizando movimiento con ID: {}", id);

        MovimientoFinanciero existente = movimientoFinancieroRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Movimiento no encontrado con ID: " + id));

        existente.setTipo(movimiento.getTipo());
        existente.setMonto(movimiento.getMonto());
        existente.setFecha(movimiento.getFecha());

        return movimientoFinancieroRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarMovimiento(Integer id) {
        log.info("Eliminando movimiento con ID: {}", id);
        movimientoFinancieroRepository.deleteById(id);
    }

    @Override
    public List<MovimientoFinanciero> obtenerMovimientosPorTipo(String tipo) {
        log.info("Buscando movimientos por tipo: {}", tipo);
        return movimientoFinancieroRepository.findByTipo(tipo);
    }

    @Override
    public List<MovimientoFinanciero> obtenerMovimientosPorFecha(LocalDate fecha) {
        log.info("Buscando movimientos por fecha: {}", fecha);
        return movimientoFinancieroRepository.findByFecha(fecha);
    }
}