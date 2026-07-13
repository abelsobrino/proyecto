package pe.edu.utp.proyecto.service.usuarios;

import pe.edu.utp.proyecto.modelo.usuarios.Rol;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de roles del sistema.
 * Define las operaciones disponibles para la entidad Rol.
 */
public interface RolService {

    /**
     * Guarda un nuevo rol en el sistema.
     * @param rol Datos del rol a guardar.
     * @return Rol guardado con su ID generado.
     */
    Rol guardarRol(Rol rol);

    /**
     * Busca un rol por su ID.
     * @param id ID del rol.
     * @return Optional con el rol encontrado o vacio.
     */
    Optional<Rol> obtenerRolPorId(Integer id);

    /**
     * Obtiene todos los roles registrados.
     * @return Lista de todos los roles.
     */
    List<Rol> obtenerTodosLosRoles();

    /**
     * Actualiza los datos de un rol existente.
     * @param id ID del rol a actualizar.
     * @param rol Datos actualizados del rol.
     * @return Rol actualizado.
     */
    Rol actualizarRol(Integer id, Rol rol);

    /**
     * Elimina un rol del sistema.
     * @param id ID del rol a eliminar.
     */
    void eliminarRol(Integer id);

    /**
     * Busca un rol por su nombre.
     * @param nombreRol Nombre del rol.
     * @return Optional con el rol encontrado o vacio.
     */
    Optional<Rol> buscarPorNombre(String nombreRol);
}