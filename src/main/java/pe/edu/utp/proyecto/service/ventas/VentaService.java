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

    // =============================================
    // CRUD
    // =============================================

    Venta guardarVenta(Venta venta);

    Optional<Venta> obtenerVentaPorId(Integer id);

    List<Venta> obtenerTodasLasVentas();

    Venta actualizarVenta(Integer id, Venta venta);

    void eliminarVenta(Integer id);

    // =============================================
    // CONSULTAS
    // =============================================

    /**
     * Obtiene ventas por estado.
     * @param estado Estado de la venta (PENDIENTE, PROCESANDO, COMPLETADA, CANCELADA).
     * @return Lista de ventas con ese estado.
     */
    List<Venta> obtenerVentasPorEstado(String estado);

    Double calcularTotalVenta(Integer id);

    // =============================================
    // METODOS DEL PATRON STATE
    // =============================================

    Venta procesarVenta(Integer id);

    Venta completarVenta(Integer id);

    Venta cancelarVenta(Integer id);
}