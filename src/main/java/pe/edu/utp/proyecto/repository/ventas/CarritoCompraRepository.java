package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;
import java.util.List;

@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Integer> {
    List<CarritoCompra> findByEstado(String estado);
}