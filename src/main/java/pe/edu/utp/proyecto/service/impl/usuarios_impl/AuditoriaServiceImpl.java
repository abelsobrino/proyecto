package pe.edu.utp.proyecto.service.impl.usuarios_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.usuarios.Auditoria;
import pe.edu.utp.proyecto.repository.usuarios_repository.AuditoriaRepository;
import pe.edu.utp.proyecto.service.usuarios_service.AuditoriaService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    @Transactional
    public Auditoria guardarAuditoria(Auditoria auditoria) {
        log.info("Guardando auditoria de accion: {}", auditoria.getAccion());
        return auditoriaRepository.save(auditoria);
    }

    @Override
    public Optional<Auditoria> obtenerAuditoriaPorId(Integer id) {
        log.debug("Buscando auditoria con ID: {}", id);
        return auditoriaRepository.findById(id);
    }

    @Override
    public List<Auditoria> obtenerTodasLasAuditorias() {
        log.info("Obteniendo todas las auditorias");
        return auditoriaRepository.findAll();
    }

    @Override
    @Transactional
    public Auditoria actualizarAuditoria(Integer id, Auditoria auditoria) {
        log.info("Actualizando auditoria con ID: {}", id);
        Auditoria existente = auditoriaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Auditoria no encontrada con ID: " + id));
        existente.setAccion(auditoria.getAccion());
        existente.setFecha(auditoria.getFecha());
        existente.setDescripcion(auditoria.getDescripcion());
        return auditoriaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarAuditoria(Integer id) {
        log.info("Eliminando auditoria con ID: {}", id);
        auditoriaRepository.deleteById(id);
    }

    @Override
    public List<Auditoria> obtenerPorAccion(String accion) {
        log.info("Obteniendo auditorias de accion: {}", accion);
        return auditoriaRepository.findByAccion(accion);
    }

    @Override
    public List<Auditoria> buscarEnDescripcion(String texto) {
        log.info("Buscando auditorias con texto: {}", texto);
        return auditoriaRepository.findByDescripcionContaining(texto);
    }
}