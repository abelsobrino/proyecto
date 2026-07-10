package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.CategoriaProducto;
import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    List<CategoriaProducto> findByNombreContaining(String nombre);
    List<CategoriaProducto> findByDescripcionContaining(String descripcion);
}