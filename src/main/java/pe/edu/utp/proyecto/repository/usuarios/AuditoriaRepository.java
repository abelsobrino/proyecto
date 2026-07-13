package pe.edu.utp.proyecto.repository.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;

import java.util.List;

/**
 * Repositorio para la entidad Auditoria.
 */
@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {

    /**
     * Busca auditorias por accion realizada.
     * @param accion Accion realizada (LOGIN, CREATE, DELETE, etc.).
     * @return Lista de auditorias con esa accion.
     */
    List<Auditoria> findByAccion(String accion);

    /**
     * Busca auditorias cuya descripcion contenga un texto.
     * @param texto Texto a buscar en la descripcion.
     * @return Lista de auditorias que coinciden.
     */
    List<Auditoria> findByDescripcionContaining(String texto);
}