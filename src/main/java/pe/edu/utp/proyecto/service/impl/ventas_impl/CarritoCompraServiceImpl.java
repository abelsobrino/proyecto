package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;
import pe.edu.utp.proyecto.repository.ventas_repository.CarritoCompraRepository;
import pe.edu.utp.proyecto.service.ventas_service.CarritoCompraService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarritoCompraServiceImpl implements CarritoCompraService {

    private final CarritoCompraRepository carritoRepository;

    @Override
    @Transactional
    public CarritoCompra guardarCarrito(CarritoCompra carrito) {
        try {
            log.info("Guardando nuevo carrito de compra");
            return carritoRepository.save(carrito);
        } catch (Exception e) {
            log.error("Error al guardar carrito: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el carrito: " + e.getMessage());
        }
    }

    @Override
    public Optional<CarritoCompra> obtenerCarritoPorId(Integer id) {
        try {
            log.debug("Buscando carrito con ID: {}", id);
            return carritoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar carrito: {}", e.getMessage());
            throw new BusinessException("Error al buscar el carrito: " + e.getMessage());
        }
    }

    @Override
    public List<CarritoCompra> obtenerTodosLosCarritos() {
        try {
            log.info("Obteniendo todos los carritos");
            return carritoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener carritos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los carritos: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public CarritoCompra actualizarCarrito(Integer id, CarritoCompra carrito) {
        try {
            log.info("Actualizando carrito con ID: {}", id);
            CarritoCompra existente = carritoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Carrito no encontrado con ID: " + id));
            existente.setFechaCreacion(carrito.getFechaCreacion());
            existente.setEstado(carrito.getEstado());
            return carritoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar carrito: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el carrito: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarCarrito(Integer id) {
        try {
            log.info("Eliminando carrito con ID: {}", id);
            if (!carritoRepository.existsById(id)) {
                throw new BusinessException("Carrito no encontrado con ID: " + id);
            }
            carritoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar carrito: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el carrito: " + e.getMessage());
        }
    }

    @Override
    public List<CarritoCompra> obtenerPorEstado(String estado) {
        try {
            log.info("Obteniendo carritos con estado: {}", estado);
            return carritoRepository.findByEstado(estado);
        } catch (Exception e) {
            log.error("Error al obtener carritos por estado: {}", e.getMessage());
            throw new BusinessException("Error al obtener los carritos: " + e.getMessage());
        }
    }
}