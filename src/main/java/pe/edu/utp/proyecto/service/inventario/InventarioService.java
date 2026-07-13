package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.Inventario;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de inventarios.
 */
public interface InventarioService {

    /**
     * Guarda un nuevo inventario en el sistema.
     * @param inventario Datos del inventario a guardar.
     * @return Inventario guardado con su ID generado.
     */
    Inventario guardarInventario(Inventario inventario);

    /**
     * Busca un inventario por su ID.
     * @param id ID del inventario.
     * @return Optional con el inventario encontrado o vacio.
     */
    Optional<Inventario> obtenerInventarioPorId(Integer id);

    /**
     * Obtiene todos los inventarios registrados.
     * @return Lista de todos los inventarios.
     */
    List<Inventario> obtenerTodosLosInventarios();

    /**
     * Actualiza los datos de un inventario existente.
     * @param id ID del inventario a actualizar.
     * @param inventario Datos actualizados del inventario.
     * @return Inventario actualizado.
     */
    Inventario actualizarInventario(Integer id, Inventario inventario);

    /**
     * Elimina un inventario del sistema.
     * @param id ID del inventario a eliminar.
     */
    void eliminarInventario(Integer id);

    /**
     * Busca inventarios cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de inventarios que coinciden.
     */
    List<Inventario> buscarPorNombre(String nombre);

    /**
     * Busca inventarios cuya descripcion contenga un texto.
     * @param descripcion Texto a buscar en la descripcion.
     * @return Lista de inventarios que coinciden.
     */
    List<Inventario> buscarPorDescripcion(String descripcion);
}