package pe.edu.utp.proyecto.service.impl.usuarios_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.usuarios.Permiso;
import pe.edu.utp.proyecto.repository.usuarios_repository.PermisoRepository;
import pe.edu.utp.proyecto.service.usuarios_service.PermisoService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class PermisoServiceImpl implements PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    @Override
    @Transactional
    public Permiso guardarPermiso(Permiso permiso) {
        log.info("Guardando permiso: {}", permiso.getNombrePermiso());
        if (permisoRepository.findByNombrePermiso(permiso.getNombrePermiso()).isPresent()) {
            throw new RuntimeException("Ya existe un permiso con el nombre: " + permiso.getNombrePermiso());
        }
        return permisoRepository.save(permiso);
    }

    @Override
    public Optional<Permiso> obtenerPermisoPorId(Integer id) {
        log.debug("Buscando permiso con ID: {}", id);
        return permisoRepository.findById(id);
    }

    @Override
    public List<Permiso> obtenerTodosLosPermisos() {
        log.info("Obteniendo todos los permisos");
        return permisoRepository.findAll();
    }

    @Override
    @Transactional
    public Permiso actualizarPermiso(Integer id, Permiso permiso) {
        log.info("Actualizando permiso con ID: {}", id);
        Permiso existente = permisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado con ID: " + id));
        existente.setNombrePermiso(permiso.getNombrePermiso());
        existente.setDescripcion(permiso.getDescripcion());
        return permisoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarPermiso(Integer id) {
        log.info("Eliminando permiso con ID: {}", id);
        permisoRepository.deleteById(id);
    }

    @Override
    public Optional<Permiso> buscarPorNombre(String nombrePermiso) {
        log.debug("Buscando permiso por nombre: {}", nombrePermiso);
        return permisoRepository.findByNombrePermiso(nombrePermiso);
    }
}