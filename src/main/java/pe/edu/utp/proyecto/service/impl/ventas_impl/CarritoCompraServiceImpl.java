package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;
import pe.edu.utp.proyecto.repository.ventas_repository.CarritoCompraRepository;
import pe.edu.utp.proyecto.service.ventas_service.CarritoCompraService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CarritoCompraServiceImpl implements CarritoCompraService {

    @Autowired
    private CarritoCompraRepository carritoRepository;

    @Override
    @Transactional
    public CarritoCompra guardarCarrito(CarritoCompra carrito) {
        log.info("Guardando nuevo carrito de compra");
        return carritoRepository.save(carrito);
    }

    @Override
    public Optional<CarritoCompra> obtenerCarritoPorId(Integer id) {
        log.debug("Buscando carrito con ID: {}", id);
        return carritoRepository.findById(id);
    }

    @Override
    public List<CarritoCompra> obtenerTodosLosCarritos() {
        log.info("Obteniendo todos los carritos");
        return carritoRepository.findAll();
    }

    @Override
    @Transactional
    public CarritoCompra actualizarCarrito(Integer id, CarritoCompra carrito) {
        log.info("Actualizando carrito con ID: {}", id);
        CarritoCompra existente = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));
        existente.setFechaCreacion(carrito.getFechaCreacion());
        existente.setEstado(carrito.getEstado());
        return carritoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarCarrito(Integer id) {
        log.info("Eliminando carrito con ID: {}", id);
        carritoRepository.deleteById(id);
    }

    @Override
    public List<CarritoCompra> obtenerPorEstado(String estado) {
        log.info("Obteniendo carritos con estado: {}", estado);
        return carritoRepository.findByEstado(estado);
    }
}