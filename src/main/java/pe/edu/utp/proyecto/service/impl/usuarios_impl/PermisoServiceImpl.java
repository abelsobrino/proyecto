package pe.edu.utp.proyecto.service.impl.usuarios_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.usuarios.Permiso;
import pe.edu.utp.proyecto.repository.usuarios_repository.PermisoRepository;
import pe.edu.utp.proyecto.service.usuarios_service.PermisoService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PermisoServiceImpl implements PermisoService {

    private final PermisoRepository permisoRepository;

    @Override
    @Transactional
    public Permiso guardarPermiso(Permiso permiso) {
        try {
            log.info("Guardando permiso: {}", permiso.getNombrePermiso());
            if (permisoRepository.findByNombrePermiso(permiso.getNombrePermiso()).isPresent()) {
                throw new BusinessException("Ya existe un permiso con el nombre: " + permiso.getNombrePermiso());
            }
            return permisoRepository.save(permiso);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar permiso: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el permiso: " + e.getMessage());
        }
    }

    @Override
    public Optional<Permiso> obtenerPermisoPorId(Integer id) {
        try {
            log.debug("Buscando permiso con ID: {}", id);
            return permisoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar permiso: {}", e.getMessage());
            throw new BusinessException("Error al buscar el permiso: " + e.getMessage());
        }
    }

    @Override
    public List<Permiso> obtenerTodosLosPermisos() {
        try {
            log.info("Obteniendo todos los permisos");
            return permisoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener permisos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los permisos: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Permiso actualizarPermiso(Integer id, Permiso permiso) {
        try {
            log.info("Actualizando permiso con ID: {}", id);
            Permiso existente = permisoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Permiso no encontrado con ID: " + id));
            existente.setNombrePermiso(permiso.getNombrePermiso());
            existente.setDescripcion(permiso.getDescripcion());
            return permisoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar permiso: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el permiso: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarPermiso(Integer id) {
        try {
            log.info("Eliminando permiso con ID: {}", id);
            if (!permisoRepository.existsById(id)) {
                throw new BusinessException("Permiso no encontrado con ID: " + id);
            }
            permisoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar permiso: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el permiso: " + e.getMessage());
        }
    }

    @Override
    public Optional<Permiso> buscarPorNombre(String nombrePermiso) {
        try {
            log.debug("Buscando permiso por nombre: {}", nombrePermiso);
            return permisoRepository.findByNombrePermiso(nombrePermiso);
        } catch (Exception e) {
            log.error("Error al buscar permiso por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar el permiso: " + e.getMessage());
        }
    }
}