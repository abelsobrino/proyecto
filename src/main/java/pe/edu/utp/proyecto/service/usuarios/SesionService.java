package pe.edu.utp.proyecto.service.usuarios;

import pe.edu.utp.proyecto.modelo.usuarios.Sesion;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de sesiones de usuarios.
 */
public interface SesionService {

    /**
     * Guarda una nueva sesion en el sistema.
     * @param sesion Datos de la sesion a guardar.
     * @return Sesion guardada con su ID generado.
     */
    Sesion guardarSesion(Sesion sesion);

    /**
     * Busca una sesion por su ID.
     * @param id ID de la sesion.
     * @return Optional con la sesion encontrada o vacio.
     */
    Optional<Sesion> obtenerSesionPorId(Integer id);

    /**
     * Obtiene todas las sesiones registradas.
     * @return Lista de todas las sesiones.
     */
    List<Sesion> obtenerTodasLasSesiones();

    /**
     * Actualiza los datos de una sesion existente.
     * @param id ID de la sesion a actualizar.
     * @param sesion Datos actualizados de la sesion.
     * @return Sesion actualizada.
     */
    Sesion actualizarSesion(Integer id, Sesion sesion);

    /**
     * Elimina una sesion del sistema.
     * @param id ID de la sesion a eliminar.
     */
    void eliminarSesion(Integer id);

    /**
     * Obtiene todas las sesiones activas.
     * @return Lista de sesiones activas.
     */
    List<Sesion> obtenerSesionesActivas();

    /**
     * Obtiene sesiones de un usuario especifico.
     * @param idUsuario ID del usuario.
     * @return Lista de sesiones del usuario.
     */
    List<Sesion> obtenerSesionesPorUsuario(Integer idUsuario);
}