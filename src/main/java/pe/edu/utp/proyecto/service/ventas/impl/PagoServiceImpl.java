package pe.edu.utp.proyecto.service.ventas.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Pago;
import pe.edu.utp.proyecto.repository.ventas.PagoRepository;
import pe.edu.utp.proyecto.service.ventas.PagoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    @Override
    public Pago guardarPago(Pago pago) {
        try {
            log.info("Guardando nuevo pago");
            return pagoRepository.save(pago);
        } catch (Exception e) {
            log.error("Error al guardar pago: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el pago: " + e.getMessage());
        }
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(Integer id) {
        try {
            log.debug("Buscando pago con ID: {}", id);
            return pagoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar pago: {}", e.getMessage());
            throw new BusinessException("Error al buscar el pago: " + e.getMessage());
        }
    }

    @Override
    public List<Pago> obtenerTodosLosPagos() {
        try {
            log.info("Obteniendo todos los pagos");
            return pagoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener pagos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los pagos: " + e.getMessage());
        }
    }

    @Override
    public Pago actualizarPago(Integer id, Pago pago) {
        try {
            log.info("Actualizando pago con ID: {}", id);
            pago.setIdPago(id);
            return pagoRepository.save(pago);
        } catch (Exception e) {
            log.error("Error al actualizar pago: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el pago: " + e.getMessage());
        }
    }

    @Override
    public void eliminarPago(Integer id) {
        try {
            log.info("Eliminando pago con ID: {}", id);
            if (!pagoRepository.existsById(id)) {
                throw new BusinessException("Pago no encontrado con ID: " + id);
            }
            pagoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar pago: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el pago: " + e.getMessage());
        }
    }

    @Override
    public List<Pago> obtenerPagosPorMonto(Double montoMinimo) {
        try {
            log.info("Obteniendo pagos con monto minimo: {}", montoMinimo);
            return pagoRepository.findAll().stream()
                    .filter(p -> p.getMonto().compareTo(BigDecimal.valueOf(montoMinimo)) >= 0)
                    .toList();
        } catch (Exception e) {
            log.error("Error al obtener pagos por monto: {}", e.getMessage());
            throw new BusinessException("Error al obtener los pagos: " + e.getMessage());
        }
    }

    @Override
    public Double calcularMontoTotalPagos() {
        try {
            log.info("Calculando monto total de pagos");
            return pagoRepository.findAll().stream()
                    .map(Pago::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .doubleValue();
        } catch (Exception e) {
            log.error("Error al calcular total de pagos: {}", e.getMessage());
            throw new BusinessException("Error al calcular el total de pagos: " + e.getMessage());
        }
    }
}