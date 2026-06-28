package pe.edu.utp.proyecto.service.impl.usuarios_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.usuarios.Sesion;
import pe.edu.utp.proyecto.repository.usuarios_repository.SesionRepository;
import pe.edu.utp.proyecto.service.usuarios_service.SesionService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class SesionServiceImpl implements SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Override
    @Transactional
    public Sesion guardarSesion(Sesion sesion) {
        log.info("Guardando nueva sesion");
        return sesionRepository.save(sesion);
    }

    @Override
    public Optional<Sesion> obtenerSesionPorId(Integer id) {
        log.debug("Buscando sesion con ID: {}", id);
        return sesionRepository.findById(id);
    }

    @Override
    public List<Sesion> obtenerTodasLasSesiones() {
        log.info("Obteniendo todas las sesiones");
        return sesionRepository.findAll();
    }

    @Override
    @Transactional
    public Sesion actualizarSesion(Integer id, Sesion sesion) {
        log.info("Actualizando sesion con ID: {}", id);
        Sesion existente = sesionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesion no encontrada con ID: " + id));
        existente.setFechaInicio(sesion.getFechaInicio());
        existente.setFechaFin(sesion.getFechaFin());
        existente.setActiva(sesion.isActiva());
        existente.setUsuario(sesion.getUsuario());
        return sesionRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarSesion(Integer id) {
        log.info("Eliminando sesion con ID: {}", id);
        sesionRepository.deleteById(id);
    }

    @Override
    public List<Sesion> obtenerSesionesActivas() {
        log.info("Obteniendo sesiones activas");
        return sesionRepository.findByActiva(true);
    }

    @Override
    public List<Sesion> obtenerSesionesPorUsuario(Integer idUsuario) {
        log.info("Obteniendo sesiones del usuario ID: {}", idUsuario);
        return sesionRepository.findByUsuarioIdUsuario(idUsuario);
    }
}