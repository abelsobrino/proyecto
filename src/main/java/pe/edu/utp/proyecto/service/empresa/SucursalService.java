package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Sucursal;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de sucursales.
 * Define las operaciones disponibles para la entidad Sucursal.
 */
public interface SucursalService {

    /**
     * Guarda una nueva sucursal en el sistema.
     * @param sucursal Datos de la sucursal a guardar.
     * @return Sucursal guardada con su ID generado.
     */
    Sucursal guardarSucursal(Sucursal sucursal);

    /**
     * Busca una sucursal por su ID.
     * @param id ID de la sucursal.
     * @return Optional con la sucursal encontrada o vacio.
     */
    Optional<Sucursal> obtenerSucursalPorId(Integer id);

    /**
     * Obtiene todas las sucursales registradas.
     * @return Lista de todas las sucursales.
     */
    List<Sucursal> obtenerTodasLasSucursales();

    /**
     * Actualiza los datos de una sucursal existente.
     * @param id ID de la sucursal a actualizar.
     * @param sucursal Datos actualizados de la sucursal.
     * @return Sucursal actualizada.
     */
    Sucursal actualizarSucursal(Integer id, Sucursal sucursal);

    /**
     * Elimina una sucursal del sistema.
     * @param id ID de la sucursal a eliminar.
     */
    void eliminarSucursal(Integer id);

    /**
     * Busca sucursales cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de sucursales que coinciden.
     */
    List<Sucursal> buscarPorNombre(String nombre);

    /**
     * Busca sucursales cuya direccion contenga un texto.
     * @param direccion Texto a buscar en la direccion.
     * @return Lista de sucursales que coinciden.
     */
    List<Sucursal> buscarPorDireccion(String direccion);
}