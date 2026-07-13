package pe.edu.utp.proyecto.service.usuarios;

import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de auditorias del sistema.
 * Las auditorias son registros historicos de solo lectura.
 */
public interface AuditoriaService {

    /**
     * Obtiene todas las auditorias registradas.
     * @return Lista de todas las auditorias.
     */
    List<Auditoria> obtenerTodasLasAuditorias();

    /**
     * Busca una auditoria por su ID.
     * @param id ID de la auditoria.
     * @return Optional con la auditoria encontrada o vacio.
     */
    Optional<Auditoria> obtenerAuditoriaPorId(Integer id);

    /**
     * Busca auditorias por accion realizada.
     * @param accion Accion realizada (LOGIN, CREATE, DELETE, etc.).
     * @return Lista de auditorias que coinciden.
     */
    List<Auditoria> obtenerPorAccion(String accion);

    /**
     * Busca auditorias cuya descripcion contenga un texto.
     * @param texto Texto a buscar en la descripcion.
     * @return Lista de auditorias que coinciden.
     */
    List<Auditoria> buscarEnDescripcion(String texto);
}