package pe.edu.utp.proyecto.repository.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su correo electronico.
     * @param correo Correo del usuario.
     * @return Optional con el usuario encontrado o vacio.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Busca usuarios por su estado (activo/inactivo).
     * @param estado Estado del usuario (true=activo, false=inactivo).
     * @return Lista de usuarios con ese estado.
     */
    List<Usuario> findByEstado(boolean estado);

    /**
     * Busca usuarios por el ID de su rol.
     * @param idRol ID del rol.
     * @return Lista de usuarios con ese rol.
     */
    List<Usuario> findByRolIdRol(Integer idRol);
}