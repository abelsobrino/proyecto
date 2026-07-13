package pe.edu.utp.proyecto.service.usuarios;

import pe.edu.utp.proyecto.modelo.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de usuarios del sistema.
 */
public interface UsuarioService {

    /**
     * Guarda un nuevo usuario en el sistema.
     * @param usuario Datos del usuario a guardar.
     * @return Usuario guardado con su ID generado.
     */
    Usuario guardarUsuario(Usuario usuario);

    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario.
     * @return Optional con el usuario encontrado o vacio.
     */
    Optional<Usuario> obtenerUsuarioPorId(Integer id);

    /**
     * Obtiene todos los usuarios registrados.
     * @return Lista de todos los usuarios.
     */
    List<Usuario> obtenerTodosLosUsuarios();

    /**
     * Actualiza los datos de un usuario existente.
     * @param id ID del usuario a actualizar.
     * @param usuario Datos actualizados del usuario.
     * @return Usuario actualizado.
     */
    Usuario actualizarUsuario(Integer id, Usuario usuario);

    /**
     * Elimina un usuario del sistema.
     * @param id ID del usuario a eliminar.
     */
    void eliminarUsuario(Integer id);

    /**
     * Busca un usuario por su correo electronico.
     * @param correo Correo del usuario.
     * @return Optional con el usuario encontrado o vacio.
     */
    Optional<Usuario> buscarPorCorreo(String correo);

    /**
     * Obtiene todos los usuarios activos.
     * @return Lista de usuarios activos.
     */
    List<Usuario> obtenerUsuariosActivos();

    /**
     * Obtiene usuarios por el ID de su rol.
     * @param idRol ID del rol.
     * @return Lista de usuarios con ese rol.
     */
    List<Usuario> obtenerUsuariosPorRol(Integer idRol);
}