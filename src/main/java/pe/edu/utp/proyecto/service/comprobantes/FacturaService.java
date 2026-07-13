package pe.edu.utp.proyecto.service.comprobantes;

import pe.edu.utp.proyecto.modelo.comprobantes.Factura;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de facturas.
 * Define las operaciones disponibles para la entidad Factura.
 */
public interface FacturaService {

    /**
     * Guarda una nueva factura en el sistema.
     * @param factura Datos de la factura a guardar.
     * @return Factura guardada con su ID generado.
     */
    Factura guardarFactura(Factura factura);

    /**
     * Busca una factura por su ID.
     * @param id ID de la factura.
     * @return Optional con la factura encontrada o vacio.
     */
    Optional<Factura> obtenerFacturaPorId(Long id);

    /**
     * Obtiene todas las facturas registradas.
     * @return Lista de todas las facturas.
     */
    List<Factura> obtenerTodasLasFacturas();

    /**
     * Actualiza los datos de una factura existente.
     * @param id ID de la factura a actualizar.
     * @param factura Datos actualizados de la factura.
     * @return Factura actualizada.
     */
    Factura actualizarFactura(Long id, Factura factura);

    /**
     * Elimina una factura del sistema.
     * @param id ID de la factura a eliminar.
     */
    void eliminarFactura(Long id);

    /**
     * Busca una factura por el RUC del cliente.
     * @param rucCliente RUC del cliente.
     * @return Factura encontrada o null.
     */
    Factura buscarPorRucCliente(String rucCliente);

    /**
     * Busca facturas cuyo RUC contenga un texto.
     * @param rucCliente Texto a buscar en el RUC.
     * @return Lista de facturas que coinciden.
     */
    List<Factura> buscarPorRucClienteContaining(String rucCliente);

    /**
     * Busca facturas cuyo total sea mayor a un valor especificado.
     * @param total Valor minimo del total.
     * @return Lista de facturas con total mayor al especificado.
     */
    List<Factura> buscarPorTotalMayor(double total);
}