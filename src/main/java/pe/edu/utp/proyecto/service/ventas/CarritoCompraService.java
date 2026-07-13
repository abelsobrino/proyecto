package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de carritos de compra.
 * Define las operaciones disponibles para la entidad CarritoCompra.
 */
public interface CarritoCompraService {

    /**
     * Guarda un nuevo carrito de compra en el sistema.
     * @param carrito Datos del carrito a guardar.
     * @return Carrito guardado con su ID generado.
     */
    CarritoCompra guardarCarrito(CarritoCompra carrito);

    /**
     * Busca un carrito de compra por su ID.
     * @param id ID del carrito.
     * @return Optional con el carrito encontrado o vacio.
     */
    Optional<CarritoCompra> obtenerCarritoPorId(Integer id);

    /**
     * Obtiene todos los carritos de compra registrados.
     * @return Lista de todos los carritos.
     */
    List<CarritoCompra> obtenerTodosLosCarritos();

    /**
     * Actualiza los datos de un carrito de compra existente.
     * @param id ID del carrito a actualizar.
     * @param carrito Datos actualizados del carrito.
     * @return Carrito actualizado.
     */
    CarritoCompra actualizarCarrito(Integer id, CarritoCompra carrito);

    /**
     * Elimina un carrito de compra del sistema.
     * @param id ID del carrito a eliminar.
     */
    void eliminarCarrito(Integer id);

    /**
     * Busca carritos de compra por estado.
     * @param estado Estado del carrito (ACTIVO, CERRADO, ABANDONADO).
     * @return Lista de carritos con ese estado.
     */
    List<CarritoCompra> obtenerPorEstado(String estado);
}