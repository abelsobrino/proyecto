package pe.edu.utp.proyecto.repository.comprobantes_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.EnvioSUNAT;

@Repository
public interface EnvioSUNATRepository extends JpaRepository<EnvioSUNAT, String> {
}