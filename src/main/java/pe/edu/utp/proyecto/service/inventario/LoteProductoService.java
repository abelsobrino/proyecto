package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de lotes de productos.
 */
public interface LoteProductoService {

    /**
     * Guarda un nuevo lote en el sistema.
     * @param lote Datos del lote a guardar.
     * @return Lote guardado con su ID generado.
     */
    LoteProducto guardarLote(LoteProducto lote);

    /**
     * Busca un lote por su ID.
     * @param id ID del lote.
     * @return Optional con el lote encontrado o vacio.
     */
    Optional<LoteProducto> obtenerLotePorId(Integer id);

    /**
     * Obtiene todos los lotes registrados.
     * @return Lista de todos los lotes.
     */
    List<LoteProducto> obtenerTodosLosLotes();

    /**
     * Actualiza los datos de un lote existente.
     * @param id ID del lote a actualizar.
     * @param lote Datos actualizados del lote.
     * @return Lote actualizado.
     */
    LoteProducto actualizarLote(Integer id, LoteProducto lote);

    /**
     * Elimina un lote del sistema.
     * @param id ID del lote a eliminar.
     */
    void eliminarLote(Integer id);

    /**
     * Busca lotes por codigo de lote.
     * @param codigoLote Codigo del lote.
     * @return Lista de lotes que coinciden.
     */
    List<LoteProducto> buscarPorCodigoLote(String codigoLote);

    /**
     * Busca lotes por fecha de vencimiento.
     * @param fechaVencimiento Fecha de vencimiento.
     * @return Lista de lotes que vencen en esa fecha.
     */
    List<LoteProducto> buscarPorFechaVencimiento(Date fechaVencimiento);
}