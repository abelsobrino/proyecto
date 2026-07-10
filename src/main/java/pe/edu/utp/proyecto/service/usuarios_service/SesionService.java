package pe.edu.utp.proyecto.service.usuarios_service;

import pe.edu.utp.proyecto.modelo.usuarios.Sesion;
import java.util.List;
import java.util.Optional;

public interface SesionService {
    Sesion guardarSesion(Sesion sesion);
    Optional<Sesion> obtenerSesionPorId(Integer id);
    List<Sesion> obtenerTodasLasSesiones();
    Sesion actualizarSesion(Integer id, Sesion sesion);
    void eliminarSesion(Integer id);
    List<Sesion> obtenerSesionesActivas();
    List<Sesion> obtenerSesionesPorUsuario(Integer idUsuario);
}