package pe.edu.utp.proyecto.repository.comprobantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;

/**
 * Repositorio para la entidad Impuesto.
 * El ID es de tipo String (tipoImpuesto).
 */
@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, String> {
    // Metodos CRUD heredados de JpaRepository
}