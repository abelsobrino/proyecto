package pe.edu.utp.proyecto.service.inventario_service;

import pe.edu.utp.proyecto.modelo.inventario.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto guardarProducto(Producto producto);
    Optional<Producto> obtenerProductoPorId(Integer id);
    List<Producto> obtenerTodosLosProductos();
    Producto actualizarProducto(Integer id, Producto producto);
    void eliminarProducto(Integer id);

    Producto buscarPorNombre(String nombre);
    List<Producto> obtenerProductosConStockBajo(Integer stockMinimo);
    List<Producto> buscarPorNombreContaining(String texto);
}