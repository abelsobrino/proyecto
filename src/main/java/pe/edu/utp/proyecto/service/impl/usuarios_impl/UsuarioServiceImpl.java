package pe.edu.utp.proyecto.service.impl.usuarios_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.usuarios.Usuario;
import pe.edu.utp.proyecto.repository.usuarios_repository.UsuarioRepository;
import pe.edu.utp.proyecto.service.usuarios_service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        log.info("Guardando usuario: {}", usuario.getCorreo());
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con el correo: " + usuario.getCorreo());
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        log.debug("Buscando usuario con ID: {}", id);
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        log.info("Actualizando usuario con ID: {}", id);
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setContraseña(usuario.getContraseña());
        existente.setEstado(usuario.isEstado());
        existente.setRol(usuario.getRol());
        return usuarioRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        log.info("Eliminando usuario con ID: {}", id);
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        log.debug("Buscando usuario por correo: {}", correo);
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> obtenerUsuariosActivos() {
        log.info("Obteniendo usuarios activos");
        return usuarioRepository.findByEstado(true);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorRol(Integer idRol) {
        log.info("Obteniendo usuarios del rol ID: {}", idRol);
        return usuarioRepository.findByRolIdRol(idRol);
    }
}