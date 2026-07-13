package pe.edu.utp.proyecto.service.usuarios;

import pe.edu.utp.proyecto.modelo.usuarios.Permiso;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de permisos del sistema.
 * Define las operaciones disponibles para la entidad Permiso.
 */
public interface PermisoService {

    /**
     * Guarda un nuevo permiso en el sistema.
     * @param permiso Datos del permiso a guardar.
     * @return Permiso guardado con su ID generado.
     */
    Permiso guardarPermiso(Permiso permiso);

    /**
     * Busca un permiso por su ID.
     * @param id ID del permiso.
     * @return Optional con el permiso encontrado o vacio.
     */
    Optional<Permiso> obtenerPermisoPorId(Integer id);

    /**
     * Obtiene todos los permisos registrados.
     * @return Lista de todos los permisos.
     */
    List<Permiso> obtenerTodosLosPermisos();

    /**
     * Actualiza los datos de un permiso existente.
     * @param id ID del permiso a actualizar.
     * @param permiso Datos actualizados del permiso.
     * @return Permiso actualizado.
     */
    Permiso actualizarPermiso(Integer id, Permiso permiso);

    /**
     * Elimina un permiso del sistema.
     * @param id ID del permiso a eliminar.
     */
    void eliminarPermiso(Integer id);

    /**
     * Busca un permiso por su nombre.
     * @param nombrePermiso Nombre del permiso.
     * @return Optional con el permiso encontrado o vacio.
     */
    Optional<Permiso> buscarPorNombre(String nombrePermiso);
}