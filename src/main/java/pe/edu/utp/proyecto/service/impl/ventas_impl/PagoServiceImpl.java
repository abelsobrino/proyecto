package pe.edu.utp.proyecto.service.impl.ventas_impl;

import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto.modelo.ventas.Pago;
import pe.edu.utp.proyecto.repository.ventas_repository.PagoRepository;
import pe.edu.utp.proyecto.service.ventas_service.PagoService;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago actualizarPago(Integer id, Pago pago) {
        pago.setIdPago(id);
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(Integer id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public List<Pago> obtenerPagosPorMonto(Double montoMinimo) {
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