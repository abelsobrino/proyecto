package pe.edu.utp.proyecto.service.usuarios;

import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;
import java.util.List;
import java.util.Optional;

public interface AuditoriaService {
    Auditoria guardarAuditoria(Auditoria auditoria);
    Optional<Auditoria> obtenerAuditoriaPorId(Integer id);
    List<Auditoria> obtenerTodasLasAuditorias();
    Auditoria actualizarAuditoria(Integer id, Auditoria auditoria);
    void eliminarAuditoria(Integer id);
    List<Auditoria> obtenerPorAccion(String accion);
    List<Auditoria> buscarEnDescripcion(String texto);
}