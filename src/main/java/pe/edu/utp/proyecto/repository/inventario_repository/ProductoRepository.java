package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Buscar por nombre exacto
    Producto findByNombre(String nombre);

    // Buscar productos con stock bajo
    List<Producto> findByStockLessThan(Integer stock);

    // Buscar por nombre que contenga un texto
    List<Producto> findByNombreContaining(String texto);
}