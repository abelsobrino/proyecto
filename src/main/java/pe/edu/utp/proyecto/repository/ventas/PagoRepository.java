package pe.edu.utp.proyecto.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.proyecto.modelo.ventas.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}