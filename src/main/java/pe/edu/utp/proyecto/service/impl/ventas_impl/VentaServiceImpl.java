package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto.modelo.ventas.Venta;
import pe.edu.utp.proyecto.repository.ventas_repository.VentaRepository;
import pe.edu.utp.proyecto.service.ventas_service.VentaService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        log.info("Guardando nueva venta");
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Integer id) {
        log.debug("Buscando venta con ID: {}", id);
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        log.info("Obteniendo todas las ventas");
        return ventaRepository.findAll();
    }

    @Override
    public Venta actualizarVenta(Integer id, Venta venta) {
        log.info("Actualizando venta con ID: {}", id);
        venta.setIdVenta(id);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Integer id) {
        log.info("Eliminando venta con ID: {}", id);
        ventaRepository.deleteById(id);
    }

    @Override
    public List<Venta> obtenerVentasPorEstado(String estado) {
        log.info("Obteniendo ventas por estado: {}", estado);
        return ventaRepository.findAll();
    }

    @Override
    public Double calcularTotalVenta(Integer id) {
        log.debug("Calculando total de venta con ID: {}", id);
        return ventaRepository.findById(id)
                .map(Venta::getTotal)
                .orElse(0.0);
    }
}