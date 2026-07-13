package pe.edu.utp.proyecto.repository.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Sucursal;

import java.util.List;

/**
 * Repositorio para la entidad Sucursal.
 */
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    /**
     * Busca sucursales cuyo nombre contenga un texto.
     * @param nombre Texto a buscar en el nombre.
     * @return Lista de sucursales que coinciden.
     */
    List<Sucursal> findByNombreContaining(String nombre);

    /**
     * Busca sucursales cuya direccion contenga un texto.
     * @param direccion Texto a buscar en la direccion.
     * @return Lista de sucursales que coinciden.
     */
    List<Sucursal> findByDireccionContaining(String direccion);
}