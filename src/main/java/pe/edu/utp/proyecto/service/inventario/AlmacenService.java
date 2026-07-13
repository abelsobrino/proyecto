package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.Almacen;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de almacenes.
 * Define las operaciones disponibles para la entidad Almacen.
 */
public interface AlmacenService {

    /**
     * Guarda un nuevo almacen en el sistema.
     * @param almacen Datos del almacen a guardar.
     * @return Almacen guardado con su ID generado.
     */
    Almacen guardarAlmacen(Almacen almacen);

    /**
     * Busca un almacen por su ID.
     * @param id ID del almacen.
     * @return Optional con el almacen encontrado o vacio.
     */
    Optional<Almacen> obtenerAlmacenPorId(Integer id);

    /**
     * Obtiene todos los almacenes registrados.
     * @return Lista de todos los almacenes.
     */
    List<Almacen> obtenerTodosLosAlmacenes();

    /**
     * Actualiza los datos de un almacen existente.
     * @param id ID del almacen a actualizar.
     * @param almacen Datos actualizados del almacen.
     * @return Almacen actualizado.
     */
    Almacen actualizarAlmacen(Integer id, Almacen almacen);

    /**
     * Elimina un almacen del sistema.
     * @param id ID del almacen a eliminar.
     */
    void eliminarAlmacen(Integer id);

    /**
     * Busca almacenes cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de almacenes que coinciden.
     */
    List<Almacen> buscarPorNombre(String nombre);

    /**
     * Busca almacenes cuyo responsable contenga un texto.
     * @param responsable Texto a buscar en el responsable.
     * @return Lista de almacenes que coinciden.
     */
    List<Almacen> buscarPorResponsable(String responsable);
}