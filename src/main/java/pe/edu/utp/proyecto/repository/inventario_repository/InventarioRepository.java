package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Inventario;
import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    List<Inventario> findByNombreContaining(String nombre);
    List<Inventario> findByDescripcionContaining(String descripcion);
}