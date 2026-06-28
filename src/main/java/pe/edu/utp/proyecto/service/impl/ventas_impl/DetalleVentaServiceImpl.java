package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;
import pe.edu.utp.proyecto.repository.ventas_repository.DetalleVentaRepository;
import pe.edu.utp.proyecto.service.patron.singleton.ConfiguracionGlobal;
import pe.edu.utp.proyecto.service.ventas_service.DetalleVentaService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        log.info("Guardando nuevo detalle de venta");

        // Validar stock
        Producto producto = detalle.getProducto();
        if (producto == null) {
            throw new RuntimeException("El detalle debe tener un producto asociado");
        }

        if (producto.getStock() < detalle.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " +
                    producto.getStock() + ", Solicitado: " + detalle.getCantidad());
        }

        // Calcular subtotal
        detalle.calcularSubtotal();

        // Actualizar stock
        producto.setStock(producto.getStock() - detalle.getCantidad());
        productoRepository.save(producto);

        return detalleVentaRepository.save(detalle);
    }

    @Override
    public Optional<DetalleVenta> obtenerDetallePorId(Integer id) {
        log.debug("Buscando detalle con ID: {}", id);
        return detalleVentaRepository.findById(id);
    }

    @Override
    public List<DetalleVenta> obtenerTodosLosDetalles() {
        log.info("Obteniendo todos los detalles de venta");
        return detalleVentaRepository.findAll();
    }

    @Override
    @Transactional
    public DetalleVenta actualizarDetalle(Integer id, DetalleVenta detalle) {
        log.info("Actualizando detalle con ID: {}", id);

        DetalleVenta existente = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + id));

        existente.setCantidad(detalle.getCantidad());
        existente.setPrecioUnitario(detalle.getPrecioUnitario());
        existente.setDescuento(detalle.getDescuento());
        existente.setProducto(detalle.getProducto());
        existente.calcularSubtotal();

        return detalleVentaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarDetalle(Integer id) {
        log.info("Eliminando detalle con ID: {}", id);
        detalleVentaRepository.deleteById(id);
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorProducto(Integer idProducto) {
        log.info("Obteniendo detalles del producto ID: {}", idProducto);
        return detalleVentaRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorRangoSubtotal(Double min, Double max) {
        log.info("Obteniendo detalles con subtotal entre {} y {}", min, max);
        return detalleVentaRepository.findBySubtotalBetween(min, max);
    }

    @Override
    public Double sumarSubtotalPorProducto(Integer idProducto) {
        log.info("Sumando subtotal del producto ID: {}", idProducto);

        // Obtener todos los detalles del producto
        List<DetalleVenta> detalles = detalleVentaRepository.findByProductoIdProducto(idProducto);

        // Sumar manualmente los subtotales
        Double total = detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();

        return total;
    }

    @Override
    public DetalleVenta calcularSubtotal(DetalleVenta detalle) {
        detalle.calcularSubtotal();
        return detalle;
    }
    @Override
    @Transactional
    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        log.info("Guardando nuevo detalle de venta");

        // Obtener configuración del Singleton
        ConfiguracionGlobal config = ConfiguracionGlobal.getInstance();
        Integer stockMinimo = config.getIntegerPropiedad("stock.minimo.alerta");

        Producto producto = detalle.getProducto();
        if (producto == null) {
            throw new RuntimeException("El detalle debe tener un producto asociado");
        }

        if (producto.getStock() < detalle.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " +
                    producto.getStock() + ", Solicitado: " + detalle.getCantidad());
        }

        // Alerta de stock bajo usando Singleton
        if (stockMinimo != null && producto.getStock() - detalle.getCantidad() < stockMinimo) {
            log.warn("ALERTA: Stock bajo para producto: {}", producto.getNombre());
        }

        detalle.calcularSubtotal();
        producto.setStock(producto.getStock() - detalle.getCantidad());
        productoRepository.save(producto);

        return detalleVentaRepository.save(detalle);
    }
}