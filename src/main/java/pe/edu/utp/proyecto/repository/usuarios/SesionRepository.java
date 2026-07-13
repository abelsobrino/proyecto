package pe.edu.utp.proyecto.repository.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Sesion;

import java.util.List;

/**
 * Repositorio para la entidad Sesion.
 */
@Repository
public interface SesionRepository extends JpaRepository<Sesion, Integer> {

    /**
     * Busca sesiones por su estado (activa/inactiva).
     * @param activa Estado de la sesion.
     * @return Lista de sesiones con ese estado.
     */
    List<Sesion> findByActiva(boolean activa);

    /**
     * Busca sesiones de un usuario especifico.
     * @param idUsuario ID del usuario.
     * @return Lista de sesiones del usuario.
     */
    List<Sesion> findByUsuarioIdUsuario(Integer idUsuario);
}