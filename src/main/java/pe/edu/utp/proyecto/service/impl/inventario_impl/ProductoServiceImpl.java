package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import pe.edu.utp.proyecto.repository.inventario_repository.ProductoRepository;
import pe.edu.utp.proyecto.service.inventario_service.ProductoService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) {
        try {
            log.info("Guardando producto: {}", producto.getNombre());
            return productoRepository.save(producto);
        } catch (Exception e) {
            log.error("Error al guardar producto: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el producto: " + e.getMessage());
        }
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Integer id) {
        try {
            log.debug("Buscando producto con ID: {}", id);
            return productoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar producto: {}", e.getMessage());
            throw new BusinessException("Error al buscar el producto: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        try {
            log.info("Obteniendo todos los productos");
            return productoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener productos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los productos: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Integer id, Producto producto) {
        try {
            log.info("Actualizando producto con ID: {}", id);
            Producto existente = productoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Producto no encontrado con ID: " + id));
            existente.setNombre(producto.getNombre());
            existente.setPrecio(producto.getPrecio());
            existente.setStock(producto.getStock());
            return productoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar producto: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el producto: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarProducto(Integer id) {
        try {
            log.info("Eliminando producto con ID: {}", id);
            if (!productoRepository.existsById(id)) {
                throw new BusinessException("Producto no encontrado con ID: " + id);
            }
            productoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar producto: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el producto: " + e.getMessage());
        }
    }

    @Override
    public Producto buscarPorNombre(String nombre) {
        try {
            log.debug("Buscando producto por nombre: {}", nombre);
            return productoRepository.findByNombre(nombre);
        } catch (Exception e) {
            log.error("Error al buscar producto por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar el producto: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> obtenerProductosConStockBajo(Integer stockMinimo) {
        try {
            log.info("Obteniendo productos con stock menor a: {}", stockMinimo);
            return productoRepository.findByStockLessThan(stockMinimo);
        } catch (Exception e) {
            log.error("Error al obtener productos con stock bajo: {}", e.getMessage());
            throw new BusinessException("Error al obtener los productos: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> buscarPorNombreContaining(String texto) {
        try {
            log.info("Buscando productos que contengan: {}", texto);
            return productoRepository.findByNombreContaining(texto);
        } catch (Exception e) {
            log.error("Error al buscar productos por texto: {}", e.getMessage());
            throw new BusinessException("Error al buscar los productos: " + e.getMessage());
        }
    }
}