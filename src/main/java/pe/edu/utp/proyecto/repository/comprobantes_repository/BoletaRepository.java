package pe.edu.utp.proyecto.repository.comprobantes_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;

import java.util.List;
 @Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
     List<Boleta> findByDniCliente(String dniCliente);

     List<Boleta> findByTotalGreaterThan(double total);
}
