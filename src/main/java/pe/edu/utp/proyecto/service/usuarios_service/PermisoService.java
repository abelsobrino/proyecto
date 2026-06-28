package pe.edu.utp.proyecto.service.usuarios_service;

import pe.edu.utp.proyecto.modelo.usuarios.Permiso;

import java.util.List;
import java.util.Optional;

public interface PermisoService {

    Permiso guardarPermiso(Permiso permiso);
    Optional<Permiso> obtenerPermisoPorId(Integer id);
    List<Permiso> obtenerTodosLosPermisos();
    Permiso actualizarPermiso(Integer id, Permiso permiso);
    void eliminarPermiso(Integer id);

    Optional<Permiso> buscarPorNombre(String nombrePermiso);
}