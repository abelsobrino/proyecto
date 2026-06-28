package pe.edu.utp.proyecto.service.inventario_service;

import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;

import java.util.List;
import java.util.Optional;

public interface CategoriaProductoService {

    // CRUD Básico
    CategoriaProducto guardarCategoria(CategoriaProducto categoria);
    Optional<CategoriaProducto> obtenerCategoriaPorId(Integer id);
    List<CategoriaProducto> obtenerTodasLasCategorias();
    CategoriaProducto actualizarCategoria(Integer id, CategoriaProducto categoria);
    void eliminarCategoria(Integer id);

    // Consultas específicas
    List<CategoriaProducto> obtenerCategoriasPorNombre(String nombre);
    List<CategoriaProducto> obtenerCategoriasPorDescripcion(String descripcion);
}