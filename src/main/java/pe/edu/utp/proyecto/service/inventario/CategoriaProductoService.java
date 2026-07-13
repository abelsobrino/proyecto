package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de categorias de productos.
 */
public interface CategoriaProductoService {

    /**
     * Guarda una nueva categoria en el sistema.
     * @param categoria Datos de la categoria a guardar.
     * @return Categoria guardada con su ID generado.
     */
    CategoriaProducto guardarCategoria(CategoriaProducto categoria);

    /**
     * Busca una categoria por su ID.
     * @param id ID de la categoria.
     * @return Optional con la categoria encontrada o vacio.
     */
    Optional<CategoriaProducto> obtenerCategoriaPorId(Integer id);

    /**
     * Obtiene todas las categorias registradas.
     * @return Lista de todas las categorias.
     */
    List<CategoriaProducto> obtenerTodasLasCategorias();

    /**
     * Actualiza los datos de una categoria existente.
     * @param id ID de la categoria a actualizar.
     * @param categoria Datos actualizados de la categoria.
     * @return Categoria actualizada.
     */
    CategoriaProducto actualizarCategoria(Integer id, CategoriaProducto categoria);

    /**
     * Elimina una categoria del sistema.
     * @param id ID de la categoria a eliminar.
     */
    void eliminarCategoria(Integer id);

    /**
     * Busca categorias cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de categorias que coinciden.
     */
    List<CategoriaProducto> buscarPorNombre(String nombre);

    /**
     * Busca categorias cuya descripcion contenga un texto.
     * @param descripcion Texto a buscar en la descripcion.
     * @return Lista de categorias que coinciden.
     */
    List<CategoriaProducto> buscarPorDescripcion(String descripcion);
}