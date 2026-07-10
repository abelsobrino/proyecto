package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByNombre(String nombre);
    List<Producto> findByStockLessThan(Integer stock);
    List<Producto> findByNombreContaining(String texto);
}