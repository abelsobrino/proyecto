package pe.edu.utp.proyecto.repository.usuarios_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar por correo (para login)
    Optional<Usuario> findByCorreo(String correo);

    // Buscar usuarios activos o inactivos
    List<Usuario> findByEstado(boolean estado);

    // Buscar usuarios por rol
    List<Usuario> findByRolIdRol(Integer idRol);
}