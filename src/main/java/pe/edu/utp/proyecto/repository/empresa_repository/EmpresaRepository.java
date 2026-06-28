package pe.edu.utp.proyecto.repository.empresa_repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Empresa;

import java.util.List;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
    Empresa findByRuc(String ruc);

    List<Empresa> findByRazonSocialContaining(String razonSocial);
}
