package pe.edu.utp.proyecto.service.usuarios_service;

import pe.edu.utp.proyecto.modelo.usuarios.Rol;
import java.util.List;
import java.util.Optional;

public interface RolService {
    Rol guardarRol(Rol rol);
    Optional<Rol> obtenerRolPorId(Integer id);
    List<Rol> obtenerTodosLosRoles();
    Rol actualizarRol(Integer id, Rol rol);
    void eliminarRol(Integer id);
    Optional<Rol> buscarPorNombre(String nombreRol);
}