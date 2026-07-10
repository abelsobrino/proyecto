package pe.edu.utp.proyecto.repository.usuarios_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Sesion;
import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Integer> {
    List<Sesion> findByActiva(boolean activa);
    List<Sesion> findByUsuarioIdUsuario(Integer idUsuario);
}