package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;
import pe.edu.utp.proyecto.repository.inventario_repository.CategoriaProductoRepository;
import pe.edu.utp.proyecto.service.inventario_service.CategoriaProductoService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    private final CategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional
    public CategoriaProducto guardarCategoria(CategoriaProducto categoria) {
        try {
            log.info("Guardando nueva categoria: {}", categoria.getNombre());
            return categoriaProductoRepository.save(categoria);
        } catch (Exception e) {
            log.error("Error al guardar categoria: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la categoria: " + e.getMessage());
        }
    }

    @Override
    public Optional<CategoriaProducto> obtenerCategoriaPorId(Integer id) {
        try {
            log.debug("Buscando categoria con ID: {}", id);
            return categoriaProductoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar categoria: {}", e.getMessage());
            throw new BusinessException("Error al buscar la categoria: " + e.getMessage());
        }
    }

    @Override
    public List<CategoriaProducto> obtenerTodasLasCategorias() {
        try {
            log.info("Obteniendo todas las categorias");
            return categoriaProductoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener categorias: {}", e.getMessage());
            throw new BusinessException("Error al obtener las categorias: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public CategoriaProducto actualizarCategoria(Integer id, CategoriaProducto categoria) {
        try {
            log.info("Actualizando categoria con ID: {}", id);
            CategoriaProducto existente = categoriaProductoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Categoria no encontrada con ID: " + id));
            existente.setNombre(categoria.getNombre());
            existente.setDescripcion(categoria.getDescripcion());
            return categoriaProductoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar categoria: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la categoria: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarCategoria(Integer id) {
        try {
            log.info("Eliminando categoria con ID: {}", id);
            if (!categoriaProductoRepository.existsById(id)) {
                throw new BusinessException("Categoria no encontrada con ID: " + id);
            }
            categoriaProductoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar categoria: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la categoria: " + e.getMessage());
        }
    }

    @Override
    public List<CategoriaProducto> obtenerCategoriasPorNombre(String nombre) {
        try {
            log.info("Buscando categorias por nombre: {}", nombre);
            return categoriaProductoRepository.findByNombreContaining(nombre);
        } catch (Exception e) {
            log.error("Error al buscar categorias por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar las categorias: " + e.getMessage());
        }
    }

    @Override
    public List<CategoriaProducto> obtenerCategoriasPorDescripcion(String descripcion) {
        try {
            log.info("Buscando categorias por descripcion: {}", descripcion);
            return categoriaProductoRepository.findByDescripcionContaining(descripcion);
        } catch (Exception e) {
            log.error("Error al buscar categorias por descripcion: {}", e.getMessage());
            throw new BusinessException("Error al buscar las categorias: " + e.getMessage());
        }
    }
}