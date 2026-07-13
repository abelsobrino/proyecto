package pe.edu.utp.proyecto.repository.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Rol;

import java.util.Optional;

/**
 * Repositorio para la entidad Rol.
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    /**
     * Busca un rol por su nombre.
     * @param nombreRol Nombre del rol.
     * @return Optional con el rol encontrado o vacio.
     */
    Optional<Rol> findByNombreRol(String nombreRol);
}