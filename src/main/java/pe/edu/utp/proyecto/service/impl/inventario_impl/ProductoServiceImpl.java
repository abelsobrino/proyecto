package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import pe.edu.utp.proyecto.repository.inventario_repository.ProductoRepository;
import pe.edu.utp.proyecto.service.inventario_service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) {
        log.info("Guardando producto: {}", producto.getNombre());
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Integer id) {
        log.debug("Buscando producto con ID: {}", id);
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        log.info("Obteniendo todos los productos");
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Integer id, Producto producto) {
        log.info("Actualizando producto con ID: {}", id);
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        return productoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarProducto(Integer id) {
        log.info("Eliminando producto con ID: {}", id);
        productoRepository.deleteById(id);
    }

    @Override
    public Producto buscarPorNombre(String nombre) {
        log.debug("Buscando producto por nombre: {}", nombre);
        return productoRepository.findByNombre(nombre);
    }

    @Override
    public List<Producto> obtenerProductosConStockBajo(Integer stockMinimo) {
        log.info("Obteniendo productos con stock menor a: {}", stockMinimo);
        return productoRepository.findByStockLessThan(stockMinimo);
    }

    @Override
    public List<Producto> buscarPorNombreContaining(String texto) {
        log.info("Buscando productos que contengan: {}", texto);
        return productoRepository.findByNombreContaining(texto);
    }
}