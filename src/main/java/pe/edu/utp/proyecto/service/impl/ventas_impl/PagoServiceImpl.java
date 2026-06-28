package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto.modelo.ventas.Pago;
import pe.edu.utp.proyecto.repository.ventas_repository.PagoRepository;
import pe.edu.utp.proyecto.service.ventas_service.PagoService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Pago guardarPago(Pago pago) {
        log.info("Guardando nuevo pago");
        return pagoRepository.save(pago);
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(Integer id) {
        log.debug("Buscando pago con ID: {}", id);
        return pagoRepository.findById(id);
    }

    @Override
    public List<Pago> obtenerTodosLosPagos() {
        log.info("Obteniendo todos los pagos");
        return pagoRepository.findAll();
    }

    @Override
    public Pago actualizarPago(Integer id, Pago pago) {
        log.info("Actualizando pago con ID: {}", id);
        pago.setIdPago(id);
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(Integer id) {
        log.info("Eliminando pago con ID: {}", id);
        pagoRepository.deleteById(id);
    }

    @Override
    public List<Pago> obtenerPagosPorMonto(Double montoMinimo) {
        log.info("Obteniendo pagos con monto mínimo: {}", montoMinimo);
        return pagoRepository.findAll();
    }

    @Override
    public Double calcularMontoTotalPagos() {
        return pagoRepository.findAll()
                .stream()
                .mapToDouble(Pago::getMonto)
                .sum();
    }
}