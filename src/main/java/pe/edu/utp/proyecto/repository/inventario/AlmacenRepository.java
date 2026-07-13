package pe.edu.utp.proyecto.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Almacen;

import java.util.List;

/**
 * Repositorio para la entidad Almacen.
 */
@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

    /**
     * Busca almacenes cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de almacenes que coinciden.
     */
    List<Almacen> findByNombreContaining(String nombre);

    /**
     * Busca almacenes cuyo responsable contenga un texto.
     * @param responsable Texto a buscar en el responsable.
     * @return Lista de almacenes que coinciden.
     */
    List<Almacen> findByResponsableContaining(String responsable);
}