package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.DetalleVenta;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de detalles de venta.
 * Define las operaciones disponibles para la entidad DetalleVenta.
 */
public interface DetalleVentaService {

    /**
     * Guarda un nuevo detalle de venta en el sistema.
     * @param detalle Datos del detalle a guardar.
     * @return Detalle guardado con su ID generado.
     */
    DetalleVenta guardarDetalle(DetalleVenta detalle);

    /**
     * Busca un detalle de venta por su ID.
     * @param id ID del detalle.
     * @return Optional con el detalle encontrado o vacio.
     */
    Optional<DetalleVenta> obtenerDetallePorId(Integer id);

    /**
     * Obtiene todos los detalles de venta registrados.
     * @return Lista de todos los detalles.
     */
    List<DetalleVenta> obtenerTodosLosDetalles();

    /**
     * Actualiza los datos de un detalle de venta existente.
     * @param id ID del detalle a actualizar.
     * @param detalle Datos actualizados del detalle.
     * @return Detalle actualizado.
     */
    DetalleVenta actualizarDetalle(Integer id, DetalleVenta detalle);

    /**
     * Elimina un detalle de venta del sistema.
     * @param id ID del detalle a eliminar.
     */
    void eliminarDetalle(Integer id);

    /**
     * Busca detalles de venta de un producto especifico.
     * @param idProducto ID del producto.
     * @return Lista de detalles del producto.
     */
    List<DetalleVenta> obtenerDetallesPorProducto(Integer idProducto);

    /**
     * Busca detalles de venta cuyo subtotal este en un rango.
     * @param min Valor minimo del subtotal.
     * @param max Valor maximo del subtotal.
     * @return Lista de detalles en el rango.
     */
    List<DetalleVenta> obtenerDetallesPorRangoSubtotal(Double min, Double max);

    /**
     * Suma el subtotal de todos los detalles de un producto.
     * @param idProducto ID del producto.
     * @return Suma de los subtotales.
     */
    Double sumarSubtotalPorProducto(Integer idProducto);

    /**
     * Calcula el subtotal de un detalle.
     * @param detalle Detalle a calcular.
     * @return Detalle con el subtotal calculado.
     */
    DetalleVenta calcularSubtotal(DetalleVenta detalle);
}