package pe.edu.utp.proyecto.service.usuarios_service;

import pe.edu.utp.proyecto.modelo.usuarios.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(Integer id);
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario actualizarUsuario(Integer id, Usuario usuario);
    void eliminarUsuario(Integer id);
    Optional<Usuario> buscarPorCorreo(String correo);
    List<Usuario> obtenerUsuariosActivos();
    List<Usuario> obtenerUsuariosPorRol(Integer idRol);
}