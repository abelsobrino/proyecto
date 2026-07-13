package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de movimientos de inventario.
 */
public interface MovimientoInventarioService {

    /**
     * Guarda un nuevo movimiento de inventario en el sistema.
     * @param movimiento Datos del movimiento a guardar.
     * @return Movimiento guardado con su ID generado.
     */
    MovimientoInventario guardarMovimiento(MovimientoInventario movimiento);

    /**
     * Busca un movimiento de inventario por su ID.
     * @param id ID del movimiento.
     * @return Optional con el movimiento encontrado o vacio.
     */
    Optional<MovimientoInventario> obtenerMovimientoPorId(Integer id);

    /**
     * Obtiene todos los movimientos de inventario registrados.
     * @return Lista de todos los movimientos.
     */
    List<MovimientoInventario> obtenerTodosLosMovimientos();

    /**
     * Actualiza los datos de un movimiento de inventario existente.
     * @param id ID del movimiento a actualizar.
     * @param movimiento Datos actualizados del movimiento.
     * @return Movimiento actualizado.
     */
    MovimientoInventario actualizarMovimiento(Integer id, MovimientoInventario movimiento);

    /**
     * Elimina un movimiento de inventario del sistema.
     * @param id ID del movimiento a eliminar.
     */
    void eliminarMovimiento(Integer id);

    /**
     * Busca movimientos por tipo (ENTRADA/SALIDA).
     * @param tipoMovimiento Tipo de movimiento.
     * @return Lista de movimientos del tipo especificado.
     */
    List<MovimientoInventario> buscarPorTipo(String tipoMovimiento);

    /**
     * Busca movimientos de un producto especifico.
     * @param idProducto ID del producto.
     * @return Lista de movimientos del producto.
     */
    List<MovimientoInventario> buscarPorProducto(Integer idProducto);

    /**
     * Busca movimientos entre dos fechas.
     * @param inicio Fecha de inicio del rango.
     * @param fin Fecha de fin del rango.
     * @return Lista de movimientos en el rango de fechas.
     */
    List<MovimientoInventario> buscarEntreFechas(Date inicio, Date fin);
}