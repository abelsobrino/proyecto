package pe.edu.utp.proyecto.service.usuarios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.usuarios.Usuario;
import pe.edu.utp.proyecto.repository.usuarios.UsuarioRepository;
import pe.edu.utp.proyecto.service.usuarios.UsuarioService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de usuarios.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Guarda un nuevo usuario.
     * @param usuario Datos del usuario.
     * @return Usuario guardado.
     */
    @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        try {
            log.info("Guardando usuario: {}", usuario.getCorreo());
            if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
                throw new BusinessException("Ya existe un usuario con el correo: " + usuario.getCorreo());
            }
            return usuarioRepository.save(usuario);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar usuario: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el usuario: " + e.getMessage());
        }
    }

    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario.
     * @return Optional con el usuario encontrado.
     */
    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        try {
            log.debug("Buscando usuario con ID: {}", id);
            return usuarioRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar usuario: {}", e.getMessage());
            throw new BusinessException("Error al buscar el usuario: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los usuarios.
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        try {
            log.info("Obteniendo todos los usuarios");
            return usuarioRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener usuarios: {}", e.getMessage());
            throw new BusinessException("Error al obtener los usuarios: " + e.getMessage());
        }
    }

    /**
     * Actualiza un usuario existente.
     * @param id ID del usuario.
     * @param usuario Datos actualizados.
     * @return Usuario actualizado.
     */
    @Override
    @Transactional
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        try {
            log.info("Actualizando usuario con ID: {}", id);
            Usuario existente = usuarioRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Usuario no encontrado con ID: " + id));
            existente.setNombre(usuario.getNombre());
            existente.setCorreo(usuario.getCorreo());
            existente.setContrasena(usuario.getContrasena());
            existente.setEstado(usuario.isEstado());
            existente.setRol(usuario.getRol());
            return usuarioRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar usuario: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el usuario: " + e.getMessage());
        }
    }

    /**
     * Elimina un usuario.
     * @param id ID del usuario a eliminar.
     */
    @Override
    @Transactional
    public void eliminarUsuario(Integer id) {
        try {
            log.info("Eliminando usuario con ID: {}", id);
            if (!usuarioRepository.existsById(id)) {
                throw new BusinessException("Usuario no encontrado con ID: " + id);
            }
            usuarioRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar usuario: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el usuario: " + e.getMessage());
        }
    }

    /**
     * Busca un usuario por correo.
     * @param correo Correo del usuario.
     * @return Optional con el usuario encontrado.
     */
    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        try {
            log.debug("Buscando usuario por correo: {}", correo);
            return usuarioRepository.findByCorreo(correo);
        } catch (Exception e) {
            log.error("Error al buscar usuario por correo: {}", e.getMessage());
            throw new BusinessException("Error al buscar el usuario: " + e.getMessage());
        }
    }

    /**
     * Obtiene usuarios activos.
     * @return Lista de usuarios activos.
     */
    @Override
    public List<Usuario> obtenerUsuariosActivos() {
        try {
            log.info("Obteniendo usuarios activos");
            return usuarioRepository.findByEstado(true);
        } catch (Exception e) {
            log.error("Error al obtener usuarios activos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los usuarios activos: " + e.getMessage());
        }
    }

    /**
     * Obtiene usuarios por rol.
     * @param idRol ID del rol.
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> obtenerUsuariosPorRol(Integer idRol) {
        try {
            log.info("Obteniendo usuarios del rol ID: {}", idRol);
            return usuarioRepository.findByRolIdRol(idRol);
        } catch (Exception e) {
            log.error("Error al obtener usuarios por rol: {}", e.getMessage());
            throw new BusinessException("Error al obtener los usuarios del rol: " + e.getMessage());
        }
    }
}