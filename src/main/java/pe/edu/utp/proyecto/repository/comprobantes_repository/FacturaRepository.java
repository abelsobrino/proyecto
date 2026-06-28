package pe.edu.utp.proyecto.repository.comprobantes_repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.Factura;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Factura findByRucCliente(String rucCliente);

    List<Factura> findByRucClienteContaining(String rucCliente);

    List<Factura> findByTotalGreaterThan(double total);
}