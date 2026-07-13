package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Proveedor;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de proveedores.
 * Define las operaciones disponibles para la entidad Proveedor.
 */
public interface ProveedorService {

    /**
     * Guarda un nuevo proveedor en el sistema.
     * @param proveedor Datos del proveedor a guardar.
     * @return Proveedor guardado con su ID generado.
     */
    Proveedor guardarProveedor(Proveedor proveedor);

    /**
     * Busca un proveedor por su ID.
     * @param id ID del proveedor.
     * @return Optional con el proveedor encontrado o vacio.
     */
    Optional<Proveedor> obtenerProveedorPorId(Integer id);

    /**
     * Obtiene todos los proveedores registrados.
     * @return Lista de todos los proveedores.
     */
    List<Proveedor> obtenerTodosLosProveedores();

    /**
     * Actualiza los datos de un proveedor existente.
     * @param id ID del proveedor a actualizar.
     * @param proveedor Datos actualizados del proveedor.
     * @return Proveedor actualizado.
     */
    Proveedor actualizarProveedor(Integer id, Proveedor proveedor);

    /**
     * Elimina un proveedor del sistema.
     * @param id ID del proveedor a eliminar.
     */
    void eliminarProveedor(Integer id);

    /**
     * Busca un proveedor por su RUC.
     * @param ruc RUC del proveedor.
     * @return Proveedor encontrado o null.
     */
    Proveedor buscarPorRuc(String ruc);

    /**
     * Busca proveedores cuya razon social contenga un texto.
     * @param razonSocial Texto a buscar en la razon social.
     * @return Lista de proveedores que coinciden.
     */
    List<Proveedor> buscarPorRazonSocialContaining(String razonSocial);
}