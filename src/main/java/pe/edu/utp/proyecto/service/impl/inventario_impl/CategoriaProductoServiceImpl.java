package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;
import pe.edu.utp.proyecto.repository.inventario_repository.CategoriaProductoRepository;
import pe.edu.utp.proyecto.service.inventario_service.CategoriaProductoService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional
    public CategoriaProducto guardarCategoria(CategoriaProducto categoria) {
        log.info("Guardando nueva categoría");
        return categoriaProductoRepository.save(categoria);
    }

    @Override
    public Optional<CategoriaProducto> obtenerCategoriaPorId(Integer id) {
        log.debug("Buscando categoría con ID: {}", id);
        return categoriaProductoRepository.findById(id);
    }

    @Override
    public List<CategoriaProducto> obtenerTodasLasCategorias() {
        log.info("Obteniendo todas las categorías");
        return categoriaProductoRepository.findAll();
    }

    @Override
    @Transactional
    public CategoriaProducto actualizarCategoria(Integer id, CategoriaProducto categoria) {
        log.info("Actualizando categoría con ID: {}", id);

        CategoriaProducto existente = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));

        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());

        return categoriaProductoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarCategoria(Integer id) {
        log.info("Eliminando categoría con ID: {}", id);
        categoriaProductoRepository.deleteById(id);
    }

    @Override
    public List<CategoriaProducto> obtenerCategoriasPorNombre(String nombre) {
        log.info("Buscando categorías por nombre: {}", nombre);
        return categoriaProductoRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<CategoriaProducto> obtenerCategoriasPorDescripcion(String descripcion) {
        log.info("Buscando categorías por descripción: {}", descripcion);
        return categoriaProductoRepository.findByDescripcionContaining(descripcion);
    }
}