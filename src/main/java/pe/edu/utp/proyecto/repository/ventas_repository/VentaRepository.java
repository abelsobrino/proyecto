package pe.edu.utp.proyecto.repository.ventas_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
}