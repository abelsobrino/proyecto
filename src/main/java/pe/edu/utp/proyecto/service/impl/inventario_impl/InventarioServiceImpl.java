package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.Inventario;
import pe.edu.utp.proyecto.repository.inventario_repository.InventarioRepository;
import pe.edu.utp.proyecto.service.inventario_service.InventarioService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    @Transactional
    public Inventario guardarInventario(Inventario inventario) {
        log.info("Guardando nuevo inventario");
        return inventarioRepository.save(inventario);
    }

    @Override
    public Optional<Inventario> obtenerInventarioPorId(Integer id) {
        log.debug("Buscando inventario con ID: {}", id);
        return inventarioRepository.findById(id);
    }

    @Override
    public List<Inventario> obtenerTodosLosInventarios() {
        log.info("Obteniendo todos los inventarios");
        return inventarioRepository.findAll();
    }

    @Override
    @Transactional
    public Inventario actualizarInventario(Integer id, Inventario inventario) {
        log.info("Actualizando inventario con ID: {}", id);

        Inventario existente = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));

        existente.setNombre(inventario.getNombre());
        existente.setDescripcion(inventario.getDescripcion());

        return inventarioRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarInventario(Integer id) {
        log.info("Eliminando inventario con ID: {}", id);
        inventarioRepository.deleteById(id);
    }

    @Override
    public List<Inventario> obtenerInventariosPorNombre(String nombre) {
        log.info("Buscando inventarios por nombre: {}", nombre);
        return inventarioRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<Inventario> obtenerInventariosPorDescripcion(String descripcion) {
        log.info("Buscando inventarios por descripción: {}", descripcion);
        return inventarioRepository.findByDescripcionContaining(descripcion);
    }
}