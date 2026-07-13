package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.Venta;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de ventas.
 * Define las operaciones disponibles para la entidad Venta.
 * Incluye metodos para el patron State (procesar, completar, cancelar).
 */
public interface VentaService {

    /**
     * Guarda una nueva venta en el sistema.
     * @param venta Datos de la venta a guardar.
     * @return Venta guardada con su ID generado.
     */
    Venta guardarVenta(Venta venta);

    /**
     * Busca una venta por su ID.
     * @param id ID de la venta.
     * @return Optional con la venta encontrada o vacio.
     */
    Optional<Venta> obtenerVentaPorId(Integer id);

    /**
     * Obtiene todas las ventas registradas.
     * @return Lista de todas las ventas.
     */
    List<Venta> obtenerTodasLasVentas();

    /**
     * Actualiza los datos de una venta existente.
     * @param id ID de la venta a actualizar.
     * @param venta Datos actualizados de la venta.
     * @return Venta actualizada.
     */
    Venta actualizarVenta(Integer id, Venta venta);

    /**
     * Elimina una venta del sistema.
     * @param id ID de la venta a eliminar.
     */
    void eliminarVenta(Integer id);

    /**
     * Calcula el total de una venta.
     * @param id ID de la venta.
     * @return Total de la venta.
     */
    Double calcularTotalVenta(Integer id);


    /**
     * Cambia el estado de la venta a PROCESANDO.
     * @param id ID de la venta.
     * @return Venta actualizada.
     */
    Venta procesarVenta(Integer id);

    /**
     * Cambia el estado de la venta a COMPLETADA.
     * @param id ID de la venta.
     * @return Venta actualizada.
     */
    Venta completarVenta(Integer id);

    /**
     * Cambia el estado de la venta a CANCELADA.
     * @param id ID de la venta.
     * @return Venta actualizada.
     */
    Venta cancelarVenta(Integer id);
}