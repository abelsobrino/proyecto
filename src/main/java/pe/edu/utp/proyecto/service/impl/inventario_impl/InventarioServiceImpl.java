package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.Inventario;
import pe.edu.utp.proyecto.repository.inventario_repository.InventarioRepository;
import pe.edu.utp.proyecto.service.inventario_service.InventarioService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;

    @Override
    @Transactional
    public Inventario guardarInventario(Inventario inventario) {
        try {
            log.info("Guardando nuevo inventario: {}", inventario.getNombre());
            return inventarioRepository.save(inventario);
        } catch (Exception e) {
            log.error("Error al guardar inventario: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el inventario: " + e.getMessage());
        }
    }

    @Override
    public Optional<Inventario> obtenerInventarioPorId(Integer id) {
        try {
            log.debug("Buscando inventario con ID: {}", id);
            return inventarioRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar inventario: {}", e.getMessage());
            throw new BusinessException("Error al buscar el inventario: " + e.getMessage());
        }
    }

    @Override
    public List<Inventario> obtenerTodosLosInventarios() {
        try {
            log.info("Obteniendo todos los inventarios");
            return inventarioRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener inventarios: {}", e.getMessage());
            throw new BusinessException("Error al obtener los inventarios: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Inventario actualizarInventario(Integer id, Inventario inventario) {
        try {
            log.info("Actualizando inventario con ID: {}", id);
            Inventario existente = inventarioRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Inventario no encontrado con ID: " + id));
            existente.setNombre(inventario.getNombre());
            existente.setDescripcion(inventario.getDescripcion());
            return inventarioRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar inventario: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el inventario: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarInventario(Integer id) {
        try {
            log.info("Eliminando inventario con ID: {}", id);
            if (!inventarioRepository.existsById(id)) {
                throw new BusinessException("Inventario no encontrado con ID: " + id);
            }
            inventarioRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar inventario: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el inventario: " + e.getMessage());
        }
    }

    @Override
    public List<Inventario> obtenerInventariosPorNombre(String nombre) {
        try {
            log.info("Buscando inventarios por nombre: {}", nombre);
            return inventarioRepository.findByNombreContaining(nombre);
        } catch (Exception e) {
            log.error("Error al buscar inventarios por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar los inventarios: " + e.getMessage());
        }
    }

    @Override
    public List<Inventario> obtenerInventariosPorDescripcion(String descripcion) {
        try {
            log.info("Buscando inventarios por descripcion: {}", descripcion);
            return inventarioRepository.findByDescripcionContaining(descripcion);
        } catch (Exception e) {
            log.error("Error al buscar inventarios por descripcion: {}", e.getMessage());
            throw new BusinessException("Error al buscar los inventarios: " + e.getMessage());
        }
    }
}