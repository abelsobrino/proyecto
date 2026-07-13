package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de metodos de pago.
 * Define las operaciones disponibles para la entidad MetodoPago.
 */
public interface MetodoPagoService {

    /**
     * Guarda un nuevo metodo de pago en el sistema.
     * @param metodoPago Datos del metodo a guardar.
     * @return Metodo guardado con su ID generado.
     */
    MetodoPago guardarMetodoPago(MetodoPago metodoPago);

    /**
     * Busca un metodo de pago por su ID.
     * @param id ID del metodo.
     * @return Optional con el metodo encontrado o vacio.
     */
    Optional<MetodoPago> obtenerMetodoPagoPorId(Integer id);

    /**
     * Obtiene todos los metodos de pago registrados.
     * @return Lista de todos los metodos.
     */
    List<MetodoPago> obtenerTodosLosMetodosPago();

    /**
     * Actualiza los datos de un metodo de pago existente.
     * @param id ID del metodo a actualizar.
     * @param metodoPago Datos actualizados del metodo.
     * @return Metodo actualizado.
     */
    MetodoPago actualizarMetodoPago(Integer id, MetodoPago metodoPago);

    /**
     * Elimina un metodo de pago del sistema.
     * @param id ID del metodo a eliminar.
     */
    void eliminarMetodoPago(Integer id);

    /**
     * Busca un metodo de pago por su nombre.
     * @param nombre Nombre del metodo de pago.
     * @return Optional con el metodo encontrado o vacio.
     */
    Optional<MetodoPago> obtenerMetodoPorNombre(String nombre);
}