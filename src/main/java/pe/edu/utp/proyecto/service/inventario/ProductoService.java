package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de productos.
 */
public interface ProductoService {

    /**
     * Guarda un nuevo producto en el sistema.
     * @param producto Datos del producto a guardar.
     * @return Producto guardado con su ID generado.
     */
    Producto guardarProducto(Producto producto);

    /**
     * Busca un producto por su ID.
     * @param id ID del producto.
     * @return Optional con el producto encontrado o vacio.
     */
    Optional<Producto> obtenerProductoPorId(Integer id);

    /**
     * Obtiene todos los productos registrados.
     * @return Lista de todos los productos.
     */
    List<Producto> obtenerTodosLosProductos();

    /**
     * Actualiza los datos de un producto existente.
     * @param id ID del producto a actualizar.
     * @param producto Datos actualizados del producto.
     * @return Producto actualizado.
     */
    Producto actualizarProducto(Integer id, Producto producto);

    /**
     * Elimina un producto del sistema.
     * @param id ID del producto a eliminar.
     */
    void eliminarProducto(Integer id);

    /**
     * Busca un producto por su nombre exacto.
     * @param nombre Nombre del producto.
     * @return Producto encontrado o null.
     */
    Producto buscarPorNombre(String nombre);

    /**
     * Obtiene productos con stock menor al especificado.
     * @param stockMinimo Valor maximo de stock.
     * @return Lista de productos con stock bajo.
     */
    List<Producto> obtenerProductosConStockBajo(Integer stockMinimo);

    /**
     * Busca productos cuyo nombre contenga un texto.
     * @param texto Texto a buscar en el nombre.
     * @return Lista de productos que coinciden.
     */
    List<Producto> buscarPorNombreContaining(String texto);
}