package pe.edu.utp.proyecto.service.impl.ventas_impl;

import org.springframework.stereotype.Service;
import pe.edu.utp.proyecto.modelo.ventas.Venta;
import pe.edu.utp.proyecto.repository.ventas_repository.VentaRepository;
import pe.edu.utp.proyecto.service.ventas_service.VentaService;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta actualizarVenta(Integer id, Venta venta) {
        venta.setIdVenta(id);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public List<Venta> obtenerVentasPorEstado(String estado) {
        return ventaRepository.findAll();
    }

    @Override
    public Double calcularTotalVenta(Integer id) {
        return ventaRepository.findById(id)
                .map(Venta::getTotal)
                .orElse(0.0);
    }
}