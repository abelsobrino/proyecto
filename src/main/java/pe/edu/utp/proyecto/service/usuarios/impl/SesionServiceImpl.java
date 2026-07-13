package pe.edu.utp.proyecto.service.usuarios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.usuarios.Sesion;
import pe.edu.utp.proyecto.repository.usuarios.SesionRepository;
import pe.edu.utp.proyecto.service.usuarios.SesionService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de sesiones.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SesionServiceImpl implements SesionService {

    private final SesionRepository sesionRepository;

    /**
     * Guarda una nueva sesion.
     * @param sesion Datos de la sesion.
     * @return Sesion guardada.
     */
    @Override
    @Transactional
    public Sesion guardarSesion(Sesion sesion) {
        try {
            log.info("Guardando nueva sesion para usuario: {}", sesion.getUsuario().getNombre());
            return sesionRepository.save(sesion);
        } catch (Exception e) {
            log.error("Error al guardar sesion: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la sesion: " + e.getMessage());
        }
    }

    /**
     * Busca una sesion por su ID.
     * @param id ID de la sesion.
     * @return Optional con la sesion encontrada.
     */
    @Override
    public Optional<Sesion> obtenerSesionPorId(Integer id) {
        try {
            log.debug("Buscando sesion con ID: {}", id);
            return sesionRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar sesion: {}", e.getMessage());
            throw new BusinessException("Error al buscar la sesion: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las sesiones.
     * @return Lista de sesiones.
     */
    @Override
    public List<Sesion> obtenerTodasLasSesiones() {
        try {
            log.info("Obteniendo todas las sesiones");
            return sesionRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener sesiones: {}", e.getMessage());
            throw new BusinessException("Error al obtener las sesiones: " + e.getMessage());
        }
    }

    /**
     * Actualiza una sesion existente.
     * @param id ID de la sesion.
     * @param sesion Datos actualizados.
     * @return Sesion actualizada.
     */
    @Override
    @Transactional
    public Sesion actualizarSesion(Integer id, Sesion sesion) {
        try {
            log.info("Actualizando sesion con ID: {}", id);
            Sesion existente = sesionRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Sesion no encontrada con ID: " + id));
            existente.setFechaInicio(sesion.getFechaInicio());
            existente.setFechaFin(sesion.getFechaFin());
            existente.setActiva(sesion.isActiva());
            existente.setUsuario(sesion.getUsuario());
            return sesionRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar sesion: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la sesion: " + e.getMessage());
        }
    }

    /**
     * Elimina una sesion.
     * @param id ID de la sesion a eliminar.
     */
    @Override
    @Transactional
    public void eliminarSesion(Integer id) {
        try {
            log.info("Eliminando sesion con ID: {}", id);
            if (!sesionRepository.existsById(id)) {
                throw new BusinessException("Sesion no encontrada con ID: " + id);
            }
            sesionRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar sesion: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la sesion: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las sesiones activas.
     * @return Lista de sesiones activas.
     */
    @Override
    public List<Sesion> obtenerSesionesActivas() {
        try {
            log.info("Obteniendo sesiones activas");
            return sesionRepository.findByActiva(true);
        } catch (Exception e) {
            log.error("Error al obtener sesiones activas: {}", e.getMessage());
            throw new BusinessException("Error al obtener las sesiones activas: " + e.getMessage());
        }
    }

    /**
     * Obtiene sesiones de un usuario especifico.
     * @param idUsuario ID del usuario.
     * @return Lista de sesiones del usuario.
     */
    @Override
    public List<Sesion> obtenerSesionesPorUsuario(Integer idUsuario) {
        try {
            log.info("Obteniendo sesiones del usuario ID: {}", idUsuario);
            return sesionRepository.findByUsuarioIdUsuario(idUsuario);
        } catch (Exception e) {
            log.error("Error al obtener sesiones por usuario: {}", e.getMessage());
            throw new BusinessException("Error al obtener las sesiones del usuario: " + e.getMessage());
        }
    }
}