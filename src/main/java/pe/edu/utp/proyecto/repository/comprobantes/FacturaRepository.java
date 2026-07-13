package pe.edu.utp.proyecto.repository.comprobantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.comprobantes.Factura;

import java.util.List;

/**
 * Repositorio para la entidad Factura.
 * Proporciona metodos CRUD y consultas personalizadas.
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    /**
     * Busca una factura por el RUC del cliente.
     * @param rucCliente RUC del cliente.
     * @return Factura encontrada o null.
     */
    Factura findByRucCliente(String rucCliente);

    /**
     * Busca facturas cuyo RUC contenga un texto.
     * @param rucCliente Texto a buscar en el RUC.
     * @return Lista de facturas que coinciden.
     */
    List<Factura> findByRucClienteContaining(String rucCliente);

    /**
     * Busca facturas cuyo total sea mayor a un valor especificado.
     * @param total Valor minimo del total.
     * @return Lista de facturas con total mayor al especificado.
     */
    List<Factura> findByTotalGreaterThan(double total);
}