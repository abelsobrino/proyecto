package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;
import pe.edu.utp.proyecto.repository.inventario_repository.ProductoRepository;
import pe.edu.utp.proyecto.repository.ventas_repository.DetalleVentaRepository;
import pe.edu.utp.proyecto.service.patron.singleton.ConfiguracionGlobal;
import pe.edu.utp.proyecto.service.ventas_service.DetalleVentaService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        try {
            log.info("Guardando nuevo detalle de venta");

            ConfiguracionGlobal config = ConfiguracionGlobal.getInstance();
            Integer stockMinimo = config.getIntegerPropiedad("stock.minimo.alerta");

            Producto producto = detalle.getProducto();
            if (producto == null) {
                throw new BusinessException("El detalle debe tener un producto asociado");
            }

            if (producto.getStock() < detalle.getCantidad()) {
                throw new BusinessException("Stock insuficiente. Disponible: " +
                        producto.getStock() + ", Solicitado: " + detalle.getCantidad());
            }

            if (stockMinimo != null && producto.getStock() - detalle.getCantidad() < stockMinimo) {
                log.warn("ALERTA: Stock bajo para producto: {}", producto.getNombre());
            }

            detalle.calcularSubtotal();
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            return detalleVentaRepository.save(detalle);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar detalle de venta: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el detalle de venta: " + e.getMessage());
        }
    }

    @Override
    public Optional<DetalleVenta> obtenerDetallePorId(Integer id) {
        try {
            log.debug("Buscando detalle con ID: {}", id);
            return detalleVentaRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar detalle: {}", e.getMessage());
            throw new BusinessException("Error al buscar el detalle: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleVenta> obtenerTodosLosDetalles() {
        try {
            log.info("Obteniendo todos los detalles de venta");
            return detalleVentaRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener detalles: {}", e.getMessage());
            throw new BusinessException("Error al obtener los detalles: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public DetalleVenta actualizarDetalle(Integer id, DetalleVenta detalle) {
        try {
            log.info("Actualizando detalle con ID: {}", id);
            DetalleVenta existente = detalleVentaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Detalle no encontrado con ID: " + id));
            existente.setCantidad(detalle.getCantidad());
            existente.setPrecioUnitario(detalle.getPrecioUnitario());
            existente.setDescuento(detalle.getDescuento());
            existente.setProducto(detalle.getProducto());
            existente.calcularSubtotal();
            return detalleVentaRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar detalle: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el detalle: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarDetalle(Integer id) {
        try {
            log.info("Eliminando detalle con ID: {}", id);
            if (!detalleVentaRepository.existsById(id)) {
                throw new BusinessException("Detalle no encontrado con ID: " + id);
            }
            detalleVentaRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar detalle: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el detalle: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorProducto(Integer idProducto) {
        try {
            log.info("Obteniendo detalles del producto ID: {}", idProducto);
            return detalleVentaRepository.findByProductoIdProducto(idProducto);
        } catch (Exception e) {
            log.error("Error al obtener detalles por producto: {}", e.getMessage());
            throw new BusinessException("Error al obtener los detalles: " + e.getMessage());
        }
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorRangoSubtotal(Double min, Double max) {
        try {
            log.info("Obteniendo detalles con subtotal entre {} y {}", min, max);
            return detalleVentaRepository.findBySubtotalBetween(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
        } catch (Exception e) {
            log.error("Error al obtener detalles por subtotal: {}", e.getMessage());
            throw new BusinessException("Error al obtener los detalles: " + e.getMessage());
        }
    }

    @Override
    public Double sumarSubtotalPorProducto(Integer idProducto) {
        try {
            log.info("Sumando subtotal del producto ID: {}", idProducto);
            List<DetalleVenta> detalles = detalleVentaRepository.findByProductoIdProducto(idProducto);
            return detalles.stream()
                    .map(DetalleVenta::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .doubleValue();
        } catch (Exception e) {
            log.error("Error al sumar subtotal por producto: {}", e.getMessage());
            throw new BusinessException("Error al sumar el subtotal: " + e.getMessage());
        }
    }

    @Override
    public DetalleVenta calcularSubtotal(DetalleVenta detalle) {
        try {
            log.info("Calculando subtotal del detalle");
            detalle.calcularSubtotal();
            return detalle;
        } catch (Exception e) {
            log.error("Error al calcular subtotal: {}", e.getMessage());
            throw new BusinessException("Error al calcular el subtotal: " + e.getMessage());
        }
    }
}