package pe.edu.utp.proyecto.repository.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Permiso;

import java.util.Optional;

/**
 * Repositorio para la entidad Permiso.
 */
@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

    /**
     * Busca un permiso por su nombre.
     * @param nombrePermiso Nombre del permiso.
     * @return Optional con el permiso encontrado o vacio.
     */
    Optional<Permiso> findByNombrePermiso(String nombrePermiso);
}