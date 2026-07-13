package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;

import java.util.List;

/**
 * Repositorio para la entidad CategoriaProducto.
 */
@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {

    /**
     * Busca categorias cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de categorias que coinciden.
     */
    List<CategoriaProducto> findByNombreContaining(String nombre);

    /**
     * Busca categorias cuya descripcion contenga un texto.
     * @param descripcion Texto a buscar en la descripcion.
     * @return Lista de categorias que coinciden.
     */
    List<CategoriaProducto> findByDescripcionContaining(String descripcion);
}