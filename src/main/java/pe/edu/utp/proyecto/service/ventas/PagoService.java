package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.Pago;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de pagos.
 * Define las operaciones disponibles para la entidad Pago.
 */
public interface PagoService {

    /**
     * Guarda un nuevo pago en el sistema.
     * @param pago Datos del pago a guardar.
     * @return Pago guardado con su ID generado.
     */
    Pago guardarPago(Pago pago);

    /**
     * Busca un pago por su ID.
     * @param id ID del pago.
     * @return Optional con el pago encontrado o vacio.
     */
    Optional<Pago> obtenerPagoPorId(Integer id);

    /**
     * Obtiene todos los pagos registrados.
     * @return Lista de todos los pagos.
     */
    List<Pago> obtenerTodosLosPagos();

    /**
     * Actualiza los datos de un pago existente.
     * @param id ID del pago a actualizar.
     * @param pago Datos actualizados del pago.
     * @return Pago actualizado.
     */
    Pago actualizarPago(Integer id, Pago pago);

    /**
     * Elimina un pago del sistema.
     * @param id ID del pago a eliminar.
     */
    void eliminarPago(Integer id);

    /**
     * Busca pagos cuyo monto sea mayor o igual al especificado.
     * @param montoMinimo Monto minimo.
     * @return Lista de pagos que coinciden.
     */
    List<Pago> obtenerPagosPorMonto(Double montoMinimo);

    /**
     * Calcula el monto total de todos los pagos.
     * @return Suma de todos los montos.
     */
    Double calcularMontoTotalPagos();
}