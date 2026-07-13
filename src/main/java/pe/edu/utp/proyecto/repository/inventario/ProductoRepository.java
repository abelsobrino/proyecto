package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Producto;

import java.util.List;

/**
 * Repositorio para la entidad Producto.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /**
     * Busca un producto por su nombre exacto.
     * @param nombre Nombre del producto.
     * @return Producto encontrado o null.
     */
    Producto findByNombre(String nombre);

    /**
     * Busca productos con stock menor al especificado.
     * @param stock Valor maximo de stock.
     * @return Lista de productos con stock bajo.
     */
    List<Producto> findByStockLessThan(Integer stock);

    /**
     * Busca productos cuyo nombre contenga un texto.
     * @param texto Texto a buscar en el nombre.
     * @return Lista de productos que coinciden.
     */
    List<Producto> findByNombreContaining(String texto);
}