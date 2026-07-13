package pe.edu.utp.proyecto.service.usuarios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;
import pe.edu.utp.proyecto.repository.usuarios.AuditoriaRepository;
import pe.edu.utp.proyecto.service.usuarios.AuditoriaService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de auditorias.
 * Las auditorias son registros historicos de solo lectura.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    /**
     * Obtiene todas las auditorias.
     * @return Lista de auditorias.
     */
    @Override
    public List<Auditoria> obtenerTodasLasAuditorias() {
        try {
            log.info("Obteniendo todas las auditorias");
            return auditoriaRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener auditorias: {}", e.getMessage());
            throw new BusinessException("Error al obtener las auditorias: " + e.getMessage());
        }
    }

    /**
     * Busca una auditoria por su ID.
     * @param id ID de la auditoria.
     * @return Optional con la auditoria encontrada.
     */
    @Override
    public Optional<Auditoria> obtenerAuditoriaPorId(Integer id) {
        try {
            log.debug("Buscando auditoria con ID: {}", id);
            return auditoriaRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar auditoria: {}", e.getMessage());
            throw new BusinessException("Error al buscar la auditoria: " + e.getMessage());
        }
    }

    /**
     * Busca auditorias por accion.
     * @param accion Accion realizada.
     * @return Lista de auditorias.
     */
    @Override
    public List<Auditoria> obtenerPorAccion(String accion) {
        try {
            log.info("Buscando auditorias por accion: {}", accion);
            return auditoriaRepository.findByAccion(accion);
        } catch (Exception e) {
            log.error("Error al buscar auditorias por accion: {}", e.getMessage());
            throw new BusinessException("Error al buscar auditorias: " + e.getMessage());
        }
    }

    /**
     * Busca auditorias por descripcion.
     * @param texto Texto a buscar.
     * @return Lista de auditorias.
     */
    @Override
    public List<Auditoria> buscarEnDescripcion(String texto) {
        try {
            log.info("Buscando auditorias por descripcion: {}", texto);
            return auditoriaRepository.findByDescripcionContaining(texto);
        } catch (Exception e) {
            log.error("Error al buscar auditorias por descripcion: {}", e.getMessage());
            throw new BusinessException("Error al buscar auditorias: " + e.getMessage());
        }
    }
}