package pe.edu.utp.proyecto.repository.comprobantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;
import java.util.List;

@Repository
public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Long> {
    List<NotaCredito> findByMotivoContaining(String motivo);
    List<NotaCredito> findByTotalGreaterThan(double total);
}