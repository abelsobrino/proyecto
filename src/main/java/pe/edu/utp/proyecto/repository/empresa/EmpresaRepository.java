package pe.edu.utp.proyecto.repository.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Empresa;

import java.util.List;

/**
 * Repositorio para la entidad Empresa.
 * El ID es de tipo String (RUC).
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    /**
     * Busca una empresa por su RUC.
     * @param ruc RUC de la empresa.
     * @return Empresa encontrada o null.
     */
    Empresa findByRuc(String ruc);

    /**
     * Busca empresas cuya razon social contenga un texto.
     * @param razonSocial Texto a buscar en la razon social.
     * @return Lista de empresas que coinciden.
     */
    List<Empresa> findByRazonSocialContaining(String razonSocial);
}