package pe.edu.utp.proyecto.service.impl.usuarios_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.usuarios.Rol;
import pe.edu.utp.proyecto.repository.usuarios_repository.RolRepository;
import pe.edu.utp.proyecto.service.usuarios_service.RolService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional
    public Rol guardarRol(Rol rol) {
        log.info("Guardando rol: {}", rol.getNombreRol());
        if (rolRepository.findByNombreRol(rol.getNombreRol()).isPresent()) {
            throw new RuntimeException("Ya existe un rol con el nombre: " + rol.getNombreRol());
        }
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> obtenerRolPorId(Integer id) {
        log.debug("Buscando rol con ID: {}", id);
        return rolRepository.findById(id);
    }

    @Override
    public List<Rol> obtenerTodosLosRoles() {
        log.info("Obteniendo todos los roles");
        return rolRepository.findAll();
    }

    @Override
    @Transactional
    public Rol actualizarRol(Integer id, Rol rol) {
        log.info("Actualizando rol con ID: {}", id);
        Rol existente = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
        existente.setNombreRol(rol.getNombreRol());
        existente.setDescripcion(rol.getDescripcion());
        return rolRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarRol(Integer id) {
        log.info("Eliminando rol con ID: {}", id);
        rolRepository.deleteById(id);
    }

    @Override
    public Optional<Rol> buscarPorNombre(String nombreRol) {
        log.debug("Buscando rol por nombre: {}", nombreRol);
        return rolRepository.findByNombreRol(nombreRol);
    }
}