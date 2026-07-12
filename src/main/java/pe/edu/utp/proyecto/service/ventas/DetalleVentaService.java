package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;
import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {
    DetalleVenta guardarDetalle(DetalleVenta detalle);
    Optional<DetalleVenta> obtenerDetallePorId(Integer id);
    List<DetalleVenta> obtenerTodosLosDetalles();
    DetalleVenta actualizarDetalle(Integer id, DetalleVenta detalle);
    void eliminarDetalle(Integer id);
    List<DetalleVenta> obtenerDetallesPorProducto(Integer idProducto);
    List<DetalleVenta> obtenerDetallesPorRangoSubtotal(Double min, Double max);
    Double sumarSubtotalPorProducto(Integer idProducto);
    DetalleVenta calcularSubtotal(DetalleVenta detalle);
}