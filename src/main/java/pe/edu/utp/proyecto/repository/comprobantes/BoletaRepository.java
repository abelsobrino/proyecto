package pe.edu.utp.proyecto.repository.comprobantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;

import java.util.List;

/**
 * Repositorio para la entidad Boleta.
 * Proporciona metodos CRUD y consultas personalizadas.
 */
@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    /**
     * Busca boletas por el DNI del cliente.
     * @param dniCliente DNI del cliente.
     * @return Lista de boletas del cliente.
     */
    List<Boleta> findByDniCliente(String dniCliente);

    /**
     * Busca boletas cuyo total sea mayor a un valor especificado.
     * @param total Valor minimo del total.
     * @return Lista de boletas con total mayor al especificado.
     */
    List<Boleta> findByTotalGreaterThan(double total);
}