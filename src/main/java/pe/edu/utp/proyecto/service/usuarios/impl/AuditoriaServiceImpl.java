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

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    @Override
    @Transactional
    public Auditoria guardarAuditoria(Auditoria auditoria) {
        try {
            log.info("Guardando auditoria de accion: {}", auditoria.getAccion());
            return auditoriaRepository.save(auditoria);
        } catch (Exception e) {
            log.error("Error al guardar auditoria: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la auditoria: " + e.getMessage());
        }
    }

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

    @Override
    @Transactional
    public Auditoria actualizarAuditoria(Integer id, Auditoria auditoria) {
        try {
            log.info("Actualizando auditoria con ID: {}", id);
            Auditoria existente = auditoriaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Auditoria no encontrada con ID: " + id));
            existente.setAccion(auditoria.getAccion());
            existente.setFecha(auditoria.getFecha());
            existente.setDescripcion(auditoria.getDescripcion());
            return auditoriaRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar auditoria: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la auditoria: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarAuditoria(Integer id) {
        try {
            log.info("Eliminando auditoria con ID: {}", id);
            if (!auditoriaRepository.existsById(id)) {
                throw new BusinessException("Auditoria no encontrada con ID: " + id);
            }
            auditoriaRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar auditoria: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la auditoria: " + e.getMessage());
        }
    }

    @Override
    public List<Auditoria> obtenerPorAccion(String accion) {
        try {
            log.info("Obteniendo auditorias de accion: {}", accion);
            return auditoriaRepository.findByAccion(accion);
        } catch (Exception e) {
            log.error("Error al obtener auditorias por accion: {}", e.getMessage());
            throw new BusinessException("Error al obtener las auditorias: " + e.getMessage());
        }
    }

    @Override
    public List<Auditoria> buscarEnDescripcion(String texto) {
        try {
            log.info("Buscando auditorias con texto: {}", texto);
            return auditoriaRepository.findByDescripcionContaining(texto);
        } catch (Exception e) {
            log.error("Error al buscar auditorias por texto: {}", e.getMessage());
            throw new BusinessException("Error al buscar las auditorias: " + e.getMessage());
        }
    }
}