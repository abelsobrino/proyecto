package pe.edu.utp.proyecto.repository.usuarios_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    List<Auditoria> findByAccion(String accion);
    List<Auditoria> findByDescripcionContaining(String texto);
}