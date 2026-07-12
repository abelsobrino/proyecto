package pe.edu.utp.proyecto.service.usuarios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.usuarios.Rol;
import pe.edu.utp.proyecto.repository.usuarios.RolRepository;
import pe.edu.utp.proyecto.service.usuarios.RolService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    @Transactional
    public Rol guardarRol(Rol rol) {
        try {
            log.info("Guardando rol: {}", rol.getNombreRol());
            if (rolRepository.findByNombreRol(rol.getNombreRol()).isPresent()) {
                throw new BusinessException("Ya existe un rol con el nombre: " + rol.getNombreRol());
            }
            return rolRepository.save(rol);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar rol: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el rol: " + e.getMessage());
        }
    }

    @Override
    public Optional<Rol> obtenerRolPorId(Integer id) {
        try {
            log.debug("Buscando rol con ID: {}", id);
            return rolRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar rol: {}", e.getMessage());
            throw new BusinessException("Error al buscar el rol: " + e.getMessage());
        }
    }

    @Override
    public List<Rol> obtenerTodosLosRoles() {
        try {
            log.info("Obteniendo todos los roles");
            return rolRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener roles: {}", e.getMessage());
            throw new BusinessException("Error al obtener los roles: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Rol actualizarRol(Integer id, Rol rol) {
        try {
            log.info("Actualizando rol con ID: {}", id);
            Rol existente = rolRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Rol no encontrado con ID: " + id));
            existente.setNombreRol(rol.getNombreRol());
            existente.setDescripcion(rol.getDescripcion());
            return rolRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar rol: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el rol: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarRol(Integer id) {
        try {
            log.info("Eliminando rol con ID: {}", id);
            if (!rolRepository.existsById(id)) {
                throw new BusinessException("Rol no encontrado con ID: " + id);
            }
            rolRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar rol: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el rol: " + e.getMessage());
        }
    }

    @Override
    public Optional<Rol> buscarPorNombre(String nombreRol) {
        try {
            log.debug("Buscando rol por nombre: {}", nombreRol);
            return rolRepository.findByNombreRol(nombreRol);
        } catch (Exception e) {
            log.error("Error al buscar rol por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar el rol: " + e.getMessage());
        }
    }
}