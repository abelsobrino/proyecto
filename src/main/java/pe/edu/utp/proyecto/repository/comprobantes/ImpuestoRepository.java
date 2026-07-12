package pe.edu.utp.proyecto.repository.comprobantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, String> {
}