package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Inventario;

import java.util.List;

/**
 * Repositorio para la entidad Inventario.
 */
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    /**
     * Busca inventarios cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de inventarios que coinciden.
     */
    List<Inventario> findByNombreContaining(String nombre);

    /**
     * Busca inventarios cuya descripcion contenga un texto.
     * @param descripcion Texto a buscar en la descripcion.
     * @return Lista de inventarios que coinciden.
     */
    List<Inventario> findByDescripcionContaining(String descripcion);
}