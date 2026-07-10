package pe.edu.utp.proyecto.repository.inventario_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.inventario.Almacen;
import java.util.List;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
    List<Almacen> findByNombreContaining(String nombre);
    List<Almacen> findByResponsableContaining(String responsable);
}